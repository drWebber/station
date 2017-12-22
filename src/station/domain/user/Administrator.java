package station.domain.user;

public class Administrator extends User {
    private int personalId;
    private String position;

    public Administrator() { }

    public void setUser(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.patronymic = user.getPatronymic();
        this.role = user.getRole();
        this.activity = user.getActivityState();
    }

    public int getPersonalId() {
        return personalId;
    }

    public void setPersonalId(int personalId) {
        this.personalId = personalId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Administrator [personalId=" + personalId + ", position="
                + position + ", login=" + login + ", password=" + password
                + ", surname=" + surname + ", patronymic=" + patronymic
                + ", role=" + role + ", activity=" + activity + ", name="
                + name + ", id=" + id + "]";
    }
}
