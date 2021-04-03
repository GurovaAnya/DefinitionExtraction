package def.controllers;

import def.exceptions.NotFoundException;
import def.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class DocumentController {

    DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/document")
    @ResponseBody
    public String uploadFile(File file, @RequestParam int corpusId) throws NotFoundException {
        //documentService.loadDocument(corpusId, file);
        return "ok";
    }

}
