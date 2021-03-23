package def.domain.thesaurus;

import def.exceptions.NotFoundException;
import def.models.DefinitionDescriptorConnection;
import def.repositories.DefinitionDescriptorConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConnectionDomain {

    DefinitionDescriptorConnectionRepository ddConnectionRepository;

    @Autowired
    public ConnectionDomain(DefinitionDescriptorConnectionRepository ddConnectionRepository) {
        this.ddConnectionRepository = ddConnectionRepository;
    }

    public DefinitionDescriptorConnection addConnection(DefinitionDescriptorConnection ddConnection) {
        return ddConnectionRepository.save(ddConnection);
    }

    public List<DefinitionDescriptorConnection> getConnections() {
        return ddConnectionRepository.findAll();
    }

    public Optional<DefinitionDescriptorConnection> getConnectionById(int id) {
        return ddConnectionRepository.findById(id);
    }

    public void deleteById(int id) {
        ddConnectionRepository.deleteById(id);

    }
}
