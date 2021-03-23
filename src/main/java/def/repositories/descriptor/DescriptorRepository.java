package def.repositories.descriptor;

import def.models.Descriptor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescriptorRepository extends JpaRepository<Descriptor, Integer>, DescriptorRepositoryCustom {
    List<Descriptor> getDescriptorByContent(String content);
}
