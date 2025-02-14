package mk.ukim.finki.mendo.web.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import mk.ukim.finki.mendo.web.mapper.CertificateMapper;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/certificates")
public class CertificateController {


    private final CertificateMapper certificateMapper;

    public CertificateController(CertificateMapper certificateMapper) {

        this.certificateMapper = certificateMapper;

    }

    @PostMapping("/{id}")                                                                   //userId, mesto(1, 2, 3, 4)
    public ResponseEntity<byte[]> generateCertificate(@PathVariable Long id, @RequestParam Map<String, String> userIdToRank) throws IOException {

        byte[] zipFile = certificateMapper.getCertifications(id, userIdToRank);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "certificates.zip");

        return ResponseEntity.ok()
                .headers(headers)
                .body(zipFile);
    }

    @GetMapping("/{id}")                                                                   //userId, mesto(1, 2, 3, 4)
    public ResponseEntity<byte[]> generateCertificate(@PathVariable Long id) throws IOException {

        byte[] zipFile = certificateMapper.getCertifications(id, new HashMap<>());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "certificates.zip");

        return ResponseEntity.ok()
                .headers(headers)
                .body(zipFile);
    }
}
