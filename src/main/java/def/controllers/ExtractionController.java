package def.controllers;

import def.services.ExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ExtractionController {

    @Autowired
    ExtractionService extractionService;

    @GetMapping("/extraction")
    public String extract(@RequestParam int corpusId, @RequestParam int[] patternIds) {
        extractionService.extractDefinitions(corpusId, patternIds);
        return null;
    }

}
