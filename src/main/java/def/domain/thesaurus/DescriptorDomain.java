package def.domain.thesaurus;

import def.models.Descriptor;
import def.repositories.descriptor.DescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DescriptorDomain {
    @Autowired
    DescriptorRepository descriptorRepository;

    public List<Descriptor> getDescriptor(String descriptor) {
        return descriptorRepository.getDescriptorByContent(descriptor);
    }
}
