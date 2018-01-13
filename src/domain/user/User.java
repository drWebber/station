package domain.user;

import java.security.NoSuchAlgorithmException;

import util.user.PasswordCryptographer;
import domain.NamedEntity;

public class User extends NamedEntity<Long> {
    private static final long serialVersionUID = 1L;
    protected String login;
    protected String password;
    protected String surname;
    protected String patronymic;
    protected Role role;
    protected boolean isActive;
    
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
    
    public void cryptPassword() throws NoSuchAlgorithmException {
        PasswordCryptographer pc = new PasswordCryptographer(login, password);
        password = pc.getCryptedPassword();
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
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setUser(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.patronymic = user.getPatronymic();
        this.role = user.getRole();
        this.isActive = user.isActive();
    }

    public User getUser() {
        return this;
    }

    public String toString() {
        return "User [login=" + login + ", password=" + password + ", surname="
                + surname + ", patronymic=" + patronymic + ", role=" + role
                + ", isActive=" + isActive + ", name=" + name + ", id=" + id
                + "]";
    }
}
