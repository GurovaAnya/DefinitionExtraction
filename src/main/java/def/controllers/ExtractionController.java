package def.controllers;

import def.dto.DefinitionDto;
import def.exceptions.NotFoundException;
import def.services.ExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExtractionController {

    ExtractionService extractionService;

    @Autowired
    public ExtractionController(ExtractionService extractionService) {
        this.extractionService = extractionService;
    }

    @PostMapping("/extraction")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<DefinitionDto> extract(@RequestParam int corpusId, @RequestParam int[] patternIds) throws NotFoundException {
        return extractionService.extractDefinitions(corpusId, patternIds);
    }


}
