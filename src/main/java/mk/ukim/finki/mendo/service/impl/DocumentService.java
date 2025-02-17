package mk.ukim.finki.mendo.service.impl;


import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;
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

  public Document saveFile(MultipartFile file) {
    Document document = new Document();
    document.setFileName(file.getOriginalFilename());
    document.setContentType(file.getContentType());

      byte[] fileBytes = null;
      try {
          fileBytes = file.getBytes();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
    document.setBase64Data(fileBytes);

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
    return document.getBase64Data();
  }
}