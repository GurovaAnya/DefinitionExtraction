package def.repositories;

import def.models.Definition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefinitionRepository extends JpaRepository<Definition, Integer> {
}
