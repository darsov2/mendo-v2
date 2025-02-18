package mk.ukim.finki.mendo.web.controllers.api;

import java.util.HashMap;
import java.util.Map;
import mk.ukim.finki.mendo.model.Document;
import mk.ukim.finki.mendo.service.impl.DocumentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
public class DocumentApiController {

  private final DocumentService documentService;

  public DocumentApiController(DocumentService documentService) {
    this.documentService = documentService;
  }

  @PostMapping("/upload-mce")
  public ResponseEntity<?> uploadFileMce(@RequestParam("file") MultipartFile file) {
    try {
      Document savedDocument = documentService.saveFile(file);
      Map<String, String> locationResp = new HashMap<>();
      locationResp.put("location", "/api/documents/download/" + savedDocument.getId());
      return ResponseEntity.ok().body(locationResp);
    } catch (Exception e) {
      return ResponseEntity.badRequest()
          .body("Failed to upload file: " + e.getMessage());
    }
  }

  @GetMapping("/download/{id}")
  public ResponseEntity<?> downloadFile(@PathVariable Long id) {
    try {
      Document document = documentService.getFile(id);
      byte[] fileData = documentService.getFileData(id);

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(document.getContentType()))
          .header(HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + document.getFileName() + "\"")
          .body(fileData);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
