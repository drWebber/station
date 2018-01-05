package domain;

public abstract class Entity<PK> {
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
