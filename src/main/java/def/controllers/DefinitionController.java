package def.controllers;

import def.dto.DefinitionDescriptorConnectionDto;
import def.dto.DefinitionDto;
import def.exceptions.BadRequestException;
import def.exceptions.NotFoundException;
import def.services.DefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DefinitionController {

    DefinitionService definitionService;

    @Autowired
    public DefinitionController(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }

    @GetMapping("/definitions")
    public @ResponseBody List<DefinitionDto> getDefinitions(){
        return definitionService.getAll();
    }

    @GetMapping("/definitions/{id}")
    @ResponseBody
    public DefinitionDto getDefinition(@PathVariable int id) throws NotFoundException{
         return definitionService.getById(id);

    }

    @PostMapping("/definitions")
    @ResponseBody
    public DefinitionDto postDefinition(@RequestBody DefinitionDto definition) throws BadRequestException {
        return definitionService.post(definition);
    }

    @PutMapping("/definitions")
    @ResponseBody
    public DefinitionDto putDefinition(
            @RequestBody DefinitionDto definitionDto) throws BadRequestException, NotFoundException {
        return definitionService.put(definitionDto);
    }

    @DeleteMapping("/definitions/{id}")
    @ResponseBody
    public String deleteDefiniton(@PathVariable int id) throws NotFoundException {
        definitionService.deleteById(id);
        return "ok";
    }
}
