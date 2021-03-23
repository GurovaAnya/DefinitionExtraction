package def.mapper;

import def.dto.DescriptorDto;
import def.models.Definition;
import def.models.Descriptor;
import def.repositories.DefinitionRepository;
import def.repositories.TextRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DescriptorMapper extends AbstractMapper<Descriptor, DescriptorDto> {

    private final ModelMapper mapper;
    private final TextRepository textRepository;
    private final DefinitionRepository definitionRepository;

    @Autowired
    public DescriptorMapper(ModelMapper mapper, TextRepository textRepository, DefinitionRepository definitionRepository) {
        super(Descriptor.class, DescriptorDto.class);
        this.mapper = mapper;
        this.textRepository = textRepository;
        this.definitionRepository = definitionRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Descriptor.class, DescriptorDto.class)
                .addMappings(m -> {
                    m.skip(DescriptorDto::setText);
                }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(DescriptorDto.class, Descriptor.class)
                .addMappings(m -> {
                    m.skip(Descriptor::setText);
                }).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsE(Descriptor source, DescriptorDto destination) {
        destination.setText(getTextId(source));
    }

    private Integer getTextId(Descriptor source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getText().getId();
    }

    @Override
    void mapSpecificFieldsD(DescriptorDto source, Descriptor destination) {
        destination.setText(textRepository.findById(source.getText()).orElse(null));
    }
}
