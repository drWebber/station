package station.domain.user;

import java.sql.Date;

public class Subscriber extends User {
    private String address;
    private Long phoneNum;
    private Date birthDay;
    private Administrator administrator;
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Long getPhoneNum() {
        return phoneNum;
    }
    
    public void setPhoneNum(Long phoneNum) {
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
        return "Subscriber [address=" + address + ", phoneNum=" + phoneNum
                + ", birthDay=" + birthDay + ", administrator=" + administrator
                + ", login=" + login + ", password=" + password + ", fullName="
                + fullName + ", role=" + role + ", id=" + id + "]";
    }
}
