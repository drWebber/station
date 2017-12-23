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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (activity ? 1231 : 1237);
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result
                + ((patronymic == null) ? 0 : patronymic.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO удалить equals & hashCode
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (activity != other.activity)
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (patronymic == null) {
            if (other.patronymic != null)
                return false;
        } else if (!patronymic.equals(other.patronymic))
            return false;
        if (role != other.role)
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", password=" + password + ", surname="
                + surname + ", patronymic=" + patronymic + ", role=" + role
                + ", activity=" + activity + ", name=" + name + ", id=" + id
                + "]";
    }
}
