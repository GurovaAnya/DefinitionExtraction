package def.domain.thesaurus;

import def.models.Definition;
import def.models.Descriptor;
import def.repositories.DefinitionRepository;
import def.repositories.descriptor.DescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DefinitionDomain {

    @Autowired
    DefinitionRepository definitionRepository;

    @Autowired
    DescriptorRepository descriptorRepository;

    public void writeToThesaurus(List<Definition> definitionList) {
        for (Definition definition: definitionList
             ) {
            Descriptor relatedDescriptor = definition.getDescriptor();
            definition.setDescriptor(descriptorRepository.save(relatedDescriptor));
            definitionRepository.save(definition);
        }
    }

    public List<Definition> getAll() {
        return definitionRepository.findAll();
    }

    public Optional<Definition> getDefinition(int id) {
        return definitionRepository.findById(id);
    }


    public Definition saveDefinition(Definition definition) {
        definition.setDescriptor(descriptorRepository.save(definition.getDescriptor()));
        Definition newDefinition = definitionRepository.save(definition);
        return newDefinition;
    }
}
