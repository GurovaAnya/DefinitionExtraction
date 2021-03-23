package def.repositories.user;

import def.models.Redactor;
import def.repositories.descriptor.DescriptorRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Redactor, Integer>, UserRepositoryCustom {
    List<Redactor> getRedactorByLogin(String login);
}
