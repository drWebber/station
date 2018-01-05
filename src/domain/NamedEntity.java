package domain;


public abstract class NamedEntity<T> extends Entity<T> {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
