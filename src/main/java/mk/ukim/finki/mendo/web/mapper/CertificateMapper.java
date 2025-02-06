package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Participation;
import mk.ukim.finki.mendo.model.enums.Rank;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.ResultsService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CertificateMapper {

    private final ParticipationMapper participationMapper;

    public CertificateMapper(ParticipationMapper participationMapper) {
        this.participationMapper = participationMapper;
    }

    public byte[] getCertifications(Long competitionId, Map<String, String> userIdToRank) throws IOException {
        List<Participation> participations = participationMapper.getParticipantsForCompetition(competitionId);

//        participations.forEach(
//                p -> p.setRank(Rank.valueOf(userIdToRank.get(p.getMendoUser().getId().toString())))
//        );

        participations.forEach(
                p -> p.setRank(Rank.HONORABLE_PARTICIPATION)
        );


        Path tempDir = Files.createTempDirectory("certificates");
        try {
            for (Participation participation : participations) {
                byte[] certificateBytes = generateCertificate(participation);
                String fileName = String.format("%s_%s.pdf",
                        participation.getMendoUser().getFullName().replace(" ", "_"),
                        participation.getRank().toString().toLowerCase());

                Path certificatePath = tempDir.resolve(fileName);
                Files.write(certificatePath, certificateBytes);
            }

            Path zipPath = tempDir.resolve("certificates.zip");
            try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
                Files.walk(tempDir)
                        .filter(path -> !Files.isDirectory(path) && path.toString().endsWith(".pdf"))
                        .forEach(path -> {
                            try {
                                ZipEntry zipEntry = new ZipEntry(path.getFileName().toString());
                                zipOut.putNextEntry(zipEntry);
                                Files.copy(path, zipOut);
                                zipOut.closeEntry();
                            } catch (IOException e) {
                                throw new RuntimeException("Error adding file to ZIP: " + path, e);
                            }
                        });
            }
            byte[] zipBytes = Files.readAllBytes(zipPath);

            // Clean up temporary files
            Files.walk(tempDir)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            // Log error but continue
                            e.printStackTrace();
                        }
                    });

            return zipBytes;

        } catch (Exception e) {
            Files.walk(tempDir)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
            throw new RuntimeException("Error generating certificates", e);
        }
    }




    public byte[] generateCertificate(Participation participation) throws IOException {
        try (PDDocument document = new PDDocument()) {
            // Create page in landscape orientation
            PDRectangle pageSize = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
            PDPage page = new PDPage(pageSize);
            document.addPage(page);


            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float width = page.getMediaBox().getWidth();    // This will be longer in landscape
                float height = page.getMediaBox().getHeight();  // This will be shorter in landscape

                // First do all graphics operations (borders, decorations, etc.)
                // Outer border
                contentStream.setLineWidth(3);
                contentStream.addRect(30, 30, width - 60, height - 60);
                contentStream.stroke();

                // Inner border
                contentStream.setLineWidth(1);
                contentStream.addRect(50, 50, width - 100, height - 100);
                contentStream.stroke();

                // Add decorative corners before text operations
                drawDecorations(contentStream, width, height, participation.getRank());

                // Now start text operations
                contentStream.beginText();

                // Title - adjusted Y position for landscape
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 36);
                String title = participation.getRank().equals(Rank.PARTICIPATION)
                        ? "Certificate of Participation"
                        : "Certificate of Achievement";

                // Center the title
                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * 36;
                float titleX = (width - titleWidth) / 2;
                contentStream.newLineAtOffset(titleX, height - 100);  // Adjusted for landscape
                contentStream.showText(title);

                // "This is to certify that"
                contentStream.setFont(PDType1Font.HELVETICA, 24);
                String certifyText = "This is to certify that";
                float certifyWidth = PDType1Font.HELVETICA.getStringWidth(certifyText) / 1000 * 24;
                float certifyX = (width - certifyWidth) / 2;
                // Move to absolute position instead of relative
                contentStream.newLineAtOffset(certifyX - titleX, -60);
                contentStream.showText(certifyText);

                // Recipient name
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 32);
                String name = participation.getMendoUser().getFullName();
                float nameWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(name) / 1000 * 32;
                float nameX = (width - nameWidth) / 2;
                contentStream.newLineAtOffset(nameX - certifyX, -50);
                contentStream.showText(name);

                // Achievement text
                contentStream.setFont(PDType1Font.HELVETICA, 24);
                String achievementText;
                if (participation.getRank().equals(Rank.PARTICIPATION)) {
                    achievementText = "has participated in";
                } else if (participation.getRank().equals(Rank.HONORABLE_PARTICIPATION)) {
                    achievementText = "is honorable";
                } else {
                    achievementText = "has achieved " + participation.getRank() + " Place in";
                }
                float achievementWidth = PDType1Font.HELVETICA.getStringWidth(achievementText) / 1000 * 24;
                float achievementX = (width - achievementWidth) / 2;
                contentStream.newLineAtOffset(achievementX - nameX, -40);
                contentStream.showText(achievementText);

                // Event name
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 28);
                String eventName = participation.getCompetition().getTitle();
                float eventWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(eventName) / 1000 * 28;
                float eventX = (width - eventWidth) / 2;
                contentStream.newLineAtOffset(eventX - achievementX, -40);
                contentStream.showText(eventName);

                // Date
                contentStream.setFont(PDType1Font.HELVETICA, 20);
                String dateText = "Date: " + participation.getCompetition().getStartDate();
                float dateWidth = PDType1Font.HELVETICA.getStringWidth(dateText) / 1000 * 20;
                float dateX = (width - dateWidth) / 2;
                contentStream.newLineAtOffset(dateX - eventX, -60);
                contentStream.showText(dateText);

                contentStream.endText();

                // Draw position-specific decorations after text
                if (!participation.getRank().equals(Rank.PARTICIPATION)) {
                    drawMedallion(contentStream, width, height, participation.getRank());
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        }
    }

    private void drawDecorations(PDPageContentStream contentStream, float width, float height, Rank rank) throws IOException {
        // Set color based on rank
        switch(rank) {
            case FIRST_PRIZE:
                contentStream.setStrokingColor(218, 165, 32); // Gold
                break;
            case SECOND_PRIZE:
                contentStream.setStrokingColor(192, 192, 192); // Silver
                break;
            case THIRD_PRIZE:
                contentStream.setStrokingColor(205, 127, 50); // Bronze
                break;
            default:
                contentStream.setStrokingColor(65, 105, 225); // Royal Blue
        }

        float cornerSize = 50;

        // Draw corner decorations - adjusted for landscape
        // Top left
        contentStream.moveTo(50, height - 50);
        contentStream.lineTo(50 + cornerSize, height - 50);
        contentStream.moveTo(50, height - 50);
        contentStream.lineTo(50, height - (50 + cornerSize));

        // Top right
        contentStream.moveTo(width - 50, height - 50);
        contentStream.lineTo(width - (50 + cornerSize), height - 50);
        contentStream.moveTo(width - 50, height - 50);
        contentStream.lineTo(width - 50, height - (50 + cornerSize));

        // Bottom left
        contentStream.moveTo(50, 50);
        contentStream.lineTo(50 + cornerSize, 50);
        contentStream.moveTo(50, 50);
        contentStream.lineTo(50, 50 + cornerSize);

        // Bottom right
        contentStream.moveTo(width - 50, 50);
        contentStream.lineTo(width - (50 + cornerSize), 50);
        contentStream.moveTo(width - 50, 50);
        contentStream.lineTo(width - 50, 50 + cornerSize);

        contentStream.stroke();
    }

    private void drawMedallion(PDPageContentStream contentStream, float width, float height, Rank rank) throws IOException {
        // Adjusted medallion position for landscape
        float medallionX = 150;
        float medallionY = 100;
        float medallionRadius = 40;

        contentStream.setLineWidth(2);
        switch(rank) {
            case FIRST_PRIZE:
                contentStream.setNonStrokingColor(218, 165, 32);
                break;
            case SECOND_PRIZE:
                contentStream.setNonStrokingColor(192, 192, 192);
                break;
            case THIRD_PRIZE:
                contentStream.setNonStrokingColor(205, 127, 50);
                break;
            default:
                return;
        }

//        contentStream.addRect(medallionX, medallionY, medallionRadius);
//        contentStream.fill();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.setNonStrokingColor(0, 0, 0);
        contentStream.newLineAtOffset(medallionX - 20, medallionY - 5);
        contentStream.showText(rank.toString());
        contentStream.endText();
    }
}
