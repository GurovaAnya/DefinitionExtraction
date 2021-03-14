package def.repositories;

import def.models.CorpusInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpusRepository extends JpaRepository<CorpusInfo, Integer> {
}
