package station.domain.user;

import station.domain.Entity;

public class User extends Entity {
    protected String login;
    protected String password;
    protected String fullName;
    protected Role role;
    private boolean activity;
    
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        return "User [login=" + login + ", password=" + password
                + ", fullName=" + fullName + ", role=" + role + ", activity="
                + activity + "]";
    }
}
