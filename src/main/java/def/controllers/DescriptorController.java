package def.controllers;

import def.dto.DescriptorDto;
import def.exceptions.BadRequestException;
import def.exceptions.NotFoundException;
import def.services.DescriptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DescriptorController {

    DescriptorService descriptorService;

    @Autowired
    public DescriptorController(DescriptorService descriptorService) {
        this.descriptorService = descriptorService;
    }

    @GetMapping("/descriptors")
    @ResponseBody
    public List<DescriptorDto> getDescriptorsByName(@RequestParam String descriptor){
        return descriptorService.getDescriptorsByName(descriptor);
    }

    @GetMapping("/descriptors/{id}")
    @ResponseBody
    public DescriptorDto getDescriptor(@PathVariable int id) throws NotFoundException {
        return descriptorService.getById(id);

    }

    @PostMapping("/descriptors")
    @ResponseBody
    public DescriptorDto postDescriptor(@RequestBody DescriptorDto descriptorDto) throws BadRequestException {
        return descriptorService.post(descriptorDto);
    }

    @PutMapping("/descriptors")
    @ResponseBody
    public DescriptorDto putDescriptor(
            @RequestBody DescriptorDto descriptorDto) throws BadRequestException, NotFoundException {
        return descriptorService.put(descriptorDto);
    }

    @DeleteMapping("/descriptors/{id}")
    @ResponseBody
    public String deleteDescriptor(@PathVariable int id) throws NotFoundException {
        descriptorService.deleteById(id);
        return "ok";
    }
}
