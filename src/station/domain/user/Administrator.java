package station.domain.user;

public class Administrator extends User {
    private String position;

    public Administrator() { }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Admitistrator [position=" + position + ", login=" + login
                + ", password=" + password + ", fullName=" + fullName
                + ", role=" + role + ", id=" + id + "]";
    }
}
