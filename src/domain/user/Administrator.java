package domain.user;


public class Administrator extends User {
    private int personalId;
    private String position;

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
                + ", role=" + role + ", isActive=" + isActive + ", name="
                + name + ", id=" + id + "]";
    }
}
