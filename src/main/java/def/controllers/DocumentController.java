package def.controllers;

import def.exceptions.NotFoundException;
import def.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentController {

    DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/corpus/{corpusId}/document")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file, @RequestParam int corpusId) throws NotFoundException {
        return ResponseEntity.ok(documentService.loadDocument(corpusId, file));
    }

}
