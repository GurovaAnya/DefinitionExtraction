package def.repositories.descriptor;

import def.models.Descriptor;

import java.util.List;

public interface DescriptorRepositoryCustom {
    List<Descriptor> getDescriptorByContent(String content);
}
