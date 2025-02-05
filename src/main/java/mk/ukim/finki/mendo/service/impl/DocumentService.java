package mk.ukim.finki.mendo.service.impl;


import jakarta.persistence.EntityNotFoundException;
import java.util.Base64;
import java.util.List;
import mk.ukim.finki.mendo.model.Document;
import mk.ukim.finki.mendo.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {

  private final DocumentRepository documentRepository;

  public DocumentService(DocumentRepository documentRepository) {
    this.documentRepository = documentRepository;
  }

  public Document saveFile(MultipartFile file) throws Exception {
    Document document = new Document();
    document.setFileName(file.getOriginalFilename());
    document.setContentType(file.getContentType());

    byte[] fileBytes = file.getBytes();
    String base64String = Base64.getEncoder().encodeToString(fileBytes);
    document.setBase64Data(base64String);

    return documentRepository.save(document);
  }

  public List<Document> getAllFiles() {
    return documentRepository.findAll();
  }

  public Document getFile(Long id) {
    return documentRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }

  public byte[] getFileData(Long id) {
    Document document = getFile(id);
    return Base64.getDecoder().decode(document.getBase64Data());
  }
}