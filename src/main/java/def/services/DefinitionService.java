package def.services;

import def.dto.DefinitionDto;
import def.mapper.DefinitionMapper;
import def.models.Definition;
import def.repositories.DefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefinitionService extends AbstractService<Definition, DefinitionDto>{

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository, DefinitionMapper definitionMapper) {
        super(Definition.class, DefinitionDto.class);
        super.repository = definitionRepository;
        super.mapper = definitionMapper;
    }
}
