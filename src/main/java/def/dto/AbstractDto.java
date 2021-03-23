package def.dto;

import java.io.Serializable;

public abstract class AbstractDto implements Serializable {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
