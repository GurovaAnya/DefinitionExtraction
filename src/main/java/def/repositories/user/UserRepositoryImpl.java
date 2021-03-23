package def.repositories.user;

import def.models.Descriptor;
import def.models.Redactor;
import def.repositories.descriptor.DescriptorRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Redactor> getRedactorByLogin(String login) {
        Query query = entityManager.createNativeQuery("SELECT u.* FROM thesaurus.user as u " +
                "WHERE u.login = ?", Redactor.class);
        query.setParameter(1, login);

        return query.getResultList();
    }
}

