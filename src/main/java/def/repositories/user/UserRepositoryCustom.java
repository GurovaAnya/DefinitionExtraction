package def.repositories.user;

import def.models.Descriptor;
import def.models.Redactor;

import java.util.List;

public interface UserRepositoryCustom {
    List<Redactor> getRedactorByLogin(String login);
}
