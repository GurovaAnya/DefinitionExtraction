package def.repositories;

import def.models.PatternInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternRepository extends JpaRepository<PatternInfo, Integer> {
}
