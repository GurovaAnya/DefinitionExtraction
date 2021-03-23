package def.controllers;

import def.dto.DefinitionDescriptorConnectionDto;
import def.exceptions.BadRequestException;
import def.exceptions.NotFoundException;
import def.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConnectionController {

    ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping("/connection")
    @ResponseBody
    public List<DefinitionDescriptorConnectionDto> getConnection(){
        return connectionService.getAll();
    }

    @GetMapping("/connection/{id}")
    @ResponseBody
    public DefinitionDescriptorConnectionDto getConnection(@PathVariable int id) throws NotFoundException{
        return connectionService.getById(id);
    }

    @PostMapping("/connection")
    @ResponseBody
    public DefinitionDescriptorConnectionDto postConnection(
            @RequestBody DefinitionDescriptorConnectionDto connectionDto) throws BadRequestException {
        return connectionService.post(connectionDto);
    }

    @PutMapping("/connection")
    @ResponseBody
    public DefinitionDescriptorConnectionDto putConnection(
            @RequestBody DefinitionDescriptorConnectionDto connectionDto) throws BadRequestException, NotFoundException {
        return connectionService.put(connectionDto);
    }

    @DeleteMapping("/connection/{id}")
    @ResponseBody
    public String deleteConnection(@PathVariable int id) throws NotFoundException {
        connectionService.deleteById(id);
        return "ok";
    }

    @PostMapping("/find_connections")
    @ResponseBody
    public List<DefinitionDescriptorConnectionDto> findConnections(@RequestParam int corpusId) throws NotFoundException{
        return connectionService.findConnections(corpusId);
    }


}
