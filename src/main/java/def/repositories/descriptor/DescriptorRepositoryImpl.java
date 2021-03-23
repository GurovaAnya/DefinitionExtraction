package def.repositories.descriptor;

import def.models.Descriptor;
import def.repositories.descriptor.DescriptorRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DescriptorRepositoryImpl implements DescriptorRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List getDescriptorByContent(String content) {
        Query query = entityManager.createNativeQuery("SELECT des.* FROM thesaurus.descriptor as des " +
                "WHERE des.content = ?", Descriptor.class);
        query.setParameter(1, content);

        return query.getResultList();
    }
}
