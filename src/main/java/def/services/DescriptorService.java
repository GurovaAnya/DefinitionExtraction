package def.services;

import def.dto.DescriptorDto;
import def.mapper.DescriptorMapper;
import def.models.Descriptor;
import def.repositories.descriptor.DescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DescriptorService extends AbstractService<Descriptor, DescriptorDto>{

    private final DescriptorRepository repository;

    @Autowired
    public DescriptorService(DescriptorRepository descriptorRepository, DescriptorMapper descriptorMapper) {
        super(Descriptor.class, DescriptorDto.class);
        this.repository = descriptorRepository;
        this.mapper = descriptorMapper;
    }

    public List<DescriptorDto> getDescriptorsByName(String descriptor) {
        List<Descriptor> descriptors =  repository.getDescriptorByContent(descriptor);
        return descriptors.stream().map(d -> mapper.toDto(d)).collect(Collectors.toList());
    }
}
