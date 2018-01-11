package domain.user;

import java.sql.Date;

public class Subscriber extends User {
    private static final long serialVersionUID = 2L;
    private String passportId;
    private Date birthDay;
    private String address;
    private Prefix prefix = new Prefix();
    private int phoneNum;
    private Administrator administrator = new Administrator();

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
                + ", isActive=" + isActive + ", name=" + name + ", id=" + id
                + "]";
    }
}
