package def.domain.thesaurus;

import def.models.Definition;
import def.models.Descriptor;
import def.repositories.DefinitionRepository;
import def.repositories.DescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
