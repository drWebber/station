package domain;

import java.io.Serializable;


public abstract class Entity<PK> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Entity [id=" + id + "]";
    }
}
