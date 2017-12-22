package station.domain.user;

import station.domain.NamedEntity;

public class User extends NamedEntity<Long> {
    protected String login;
    protected String password;
    protected String surname;
    protected String patronymic;
    protected Role role;
    protected boolean activity;
    
    public User() { }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public boolean getActivityState() {
        return activity;
    }
    
    public void setActivityState(boolean active) {
        this.activity = active;
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", password=" + password + ", surname="
                + surname + ", patronymic=" + patronymic + ", role=" + role
                + ", activity=" + activity + ", name=" + name + ", id=" + id
                + "]";
    }
}
