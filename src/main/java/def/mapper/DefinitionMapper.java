package def.mapper;

import def.dto.DefinitionDto;
import def.models.Definition;
import def.repositories.descriptor.DescriptorRepository;
import def.repositories.TextRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DefinitionMapper extends AbstractMapper<Definition, DefinitionDto> {

    private final ModelMapper mapper;
    private final TextRepository textRepository;
    private final DescriptorRepository descriptorRepository;

    @Autowired
    public DefinitionMapper(ModelMapper mapper, TextRepository textRepository, DescriptorRepository descriptorRepository) {
        super(Definition.class, DefinitionDto.class);
        this.mapper = mapper;
        this.textRepository = textRepository;
        this.descriptorRepository = descriptorRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Definition.class, DefinitionDto.class)
                .addMappings(m -> {
                    m.skip(DefinitionDto::setText);
                    m.skip(DefinitionDto::setDescriptor);
                }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(DefinitionDto.class, Definition.class)
                .addMappings(m -> {
                    m.skip(Definition::setText);
                    m.skip(Definition::setDescriptor);
                }).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsE(Definition source, DefinitionDto destination) {
        destination.setText(getTextId(source));
        destination.setDescriptor(getDescriptorId(source));
    }

    private Integer getDescriptorId(Definition source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getDescriptor().getId();
    }

    private Integer getTextId(Definition source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getText().getId();
    }

    @Override
    void mapSpecificFieldsD(DefinitionDto source, Definition destination) {
        destination.setText(textRepository.findById(source.getText()).orElse(null));
        destination.setDescriptor(descriptorRepository.findById(source.getDescriptor()).orElse(null));
    }

}
