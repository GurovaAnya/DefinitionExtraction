package def.services;

import def.dto.DefinitionDescriptorConnectionDto;
import def.exceptions.NotFoundException;
import def.mapper.DefinitionDescriptorConnectionMapper;
import def.models.*;
import def.repositories.CorpusRepository;
import def.repositories.DefinitionDescriptorConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionService extends AbstractService<DefinitionDescriptorConnection, DefinitionDescriptorConnectionDto>{

    private final CorpusRepository corpusRepository;
    private final DefinitionDescriptorConnectionRepository repository;


    @Autowired
    public ConnectionService(DefinitionDescriptorConnectionMapper mapper, DefinitionDescriptorConnectionRepository repository, CorpusRepository corpusRepository) {
        super(DefinitionDescriptorConnection.class, DefinitionDescriptorConnectionDto.class);
        this.mapper = mapper;
        this.repository = repository;
        this.corpusRepository = corpusRepository;
    }


    public List<DefinitionDescriptorConnectionDto> findConnections(int corpusId) throws NotFoundException {
        List<DefinitionDescriptorConnectionDto> answer = new ArrayList<>();
        List<Descriptor> descriptors = new ArrayList<>();
        List<Definition> definitions = new ArrayList<>();

        Optional<CorpusInfo> corpusInfo = corpusRepository.findById(corpusId);

        if (corpusInfo.isEmpty())
            throw new NotFoundException("Не найден корпус с данным идентификатором");

        for (Text text: corpusInfo.get().getIncludedTexts()
             ) {
            descriptors.addAll(text.getDescriptors());
        }
        for (Descriptor descriptor: descriptors
             ) {
            definitions.addAll(descriptor.getDefinitions());
        }

        for (Descriptor descriptor: descriptors
             ) {
            for (Definition definition: definitions
                 ) {
                int index = definition.getContent().indexOf(descriptor.getContent().trim());
                if (index!=-1) {
                    DefinitionDescriptorConnection ddConnection =
                            new DefinitionDescriptorConnection(index,
                                    descriptor.getContent().length(),
                                    descriptor, definition);
                    answer.add(mapper.toDto(repository.save(ddConnection)));
                }
            }
        }
        return answer;
    }

    /*public List<DefinitionDescriptorConnectionDto> getConnections() {
        List<DefinitionDescriptorConnection> connections = repository.getConnections();
        return connections.stream().map(c -> mapper.toDto(c)).collect(Collectors.toList());
    }

    public DefinitionDescriptorConnectionDto getConnection(int id) throws NotFoundException {
        Optional<DefinitionDescriptorConnection> connection = connectionDomain.getConnectionById(id);
        if (connection.isEmpty())
            throw new NotFoundException("Не найдено соединения с идентификатором " + id);
        return mapper.toDto(connection.get());
    }

    public DefinitionDescriptorConnectionDto postConnection(DefinitionDescriptorConnectionDto connectionDto)
            throws BadRequestException {
        DefinitionDescriptorConnection connection = mapper.toEntity(connectionDto);
        try{
        connection = connectionDomain.addConnection(connection);}
        catch (DataIntegrityViolationException ex){
            throw new BadRequestException("Невозможно создать соединение с данными ссылками");
        }
        return mapper.toDto(connection);
    }

    public DefinitionDescriptorConnectionDto putConnection(DefinitionDescriptorConnectionDto connectionDto) {
        DefinitionDescriptorConnection connection = mapper.toEntity(connectionDto);
        connection = connectionDomain.addConnection(connection);
        return mapper.toDto(connection);
    }

    public void deleteConnectionById(int id) throws NotFoundException{
        try {
            connectionDomain.deleteById(id);
        }
        catch (Exception ex){
            throw new NotFoundException("Не найдено соединения с идентификатором " + id);
        }
    }*/
}
