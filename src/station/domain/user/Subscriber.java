package station.domain.user;

import java.sql.Date;

public class Subscriber extends User {
    private String passportId;
    private Date birthDay;
    private String address;
    private Prefix prefix;
    private int phoneNum;
    private Administrator administrator;
    
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
    
    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    public int getPhoneNum() {
        return phoneNum;
    }
    
    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public Date getBirthDay() {
        return birthDay;
    }
    
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    
    public Administrator getAdministrator() {
        return administrator;
    }
    
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "Subscriber [passportId=" + passportId + ", birthDay="
                + birthDay + ", address=" + address + ", prefix=" + prefix
                + ", phoneNum=" + phoneNum + ", administrator=" + administrator
                + ", login=" + login + ", password=" + password + ", surname="
                + surname + ", patronymic=" + patronymic + ", role=" + role
                + ", activity=" + activity + ", name=" + name + ", id=" + id
                + "]";
    }
}
