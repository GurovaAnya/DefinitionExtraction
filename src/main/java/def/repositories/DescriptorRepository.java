package def.repositories;

import def.models.Descriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptorRepository extends JpaRepository<Descriptor, Integer> {
}
