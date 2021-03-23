package def.mapper;

import def.dto.DefinitionDescriptorConnectionDto;
import def.models.DefinitionDescriptorConnection;
import def.repositories.DefinitionRepository;
import def.repositories.descriptor.DescriptorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DefinitionDescriptorConnectionMapper extends AbstractMapper<DefinitionDescriptorConnection, DefinitionDescriptorConnectionDto> {

    private final ModelMapper mapper;
    private final DescriptorRepository descriptorRepository;
    private final DefinitionRepository definitionRepository;

    @Autowired
    public DefinitionDescriptorConnectionMapper(ModelMapper mapper, DescriptorRepository descriptorRepository, DefinitionRepository definitionRepository) {
        super(DefinitionDescriptorConnection.class, DefinitionDescriptorConnectionDto.class);
        this.mapper = mapper;
        this.descriptorRepository = descriptorRepository;
        this.definitionRepository = definitionRepository;
    }



    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(DefinitionDescriptorConnection.class, DefinitionDescriptorConnectionDto.class)
                .addMappings(m -> {
                    m.skip(DefinitionDescriptorConnectionDto::setDefinition);
                    m.skip(DefinitionDescriptorConnectionDto::setDescriptor);
                }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(DefinitionDescriptorConnectionDto.class, DefinitionDescriptorConnection.class)
                .addMappings(m -> {
                    m.skip(DefinitionDescriptorConnection::setDefinition);
                    m.skip(DefinitionDescriptorConnection::setDescriptor);
                }).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsE(DefinitionDescriptorConnection source, DefinitionDescriptorConnectionDto destination) {
        destination.setDefinition(getDefinitionId(source));
        destination.setDescriptor(getDescriptorId(source));
    }

    private Integer getDescriptorId(DefinitionDescriptorConnection source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getDescriptor().getId();
    }

    private Integer getDefinitionId(DefinitionDescriptorConnection source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getDefinition().getId();
    }

    @Override
    void mapSpecificFieldsD(DefinitionDescriptorConnectionDto source, DefinitionDescriptorConnection destination) {
        destination.setDefinition(definitionRepository.findById(source.getDefinition()).orElse(null));
        destination.setDescriptor(descriptorRepository.findById(source.getDescriptor()).orElse(null));
    }

}
