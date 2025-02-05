package mk.ukim.finki.mendo.web.controllers;


import mk.ukim.finki.mendo.service.impl.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/documents")
public class DocumentController {

  private DocumentService documentService;

  public DocumentController(DocumentService documentService) {
    this.documentService = documentService;
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("documents", documentService.getAllFiles());
    return "upload";
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {
    try {
      documentService.saveFile(file);
      redirectAttributes.addFlashAttribute("message",
          "File uploaded successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error",
          "Failed to upload file: " + e.getMessage());
    }
    return "redirect:/";
  }
}
