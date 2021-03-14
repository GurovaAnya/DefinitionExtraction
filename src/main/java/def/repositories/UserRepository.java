package def.repositories;

import def.models.Redactor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Redactor, Integer> {
}
