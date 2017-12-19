package station.domain;


public abstract class NamedEntity extends Entity{
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
