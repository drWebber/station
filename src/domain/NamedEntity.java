package domain;


public abstract class NamedEntity<T> extends Entity<T> {
    private static final long serialVersionUID = 1L;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
