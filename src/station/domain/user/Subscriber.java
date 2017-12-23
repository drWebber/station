package station.domain.user;

import java.sql.Date;

import station.domain.Entity;

public class Subscriber extends Entity<Long> {
    private User user;
    private String passportId;
    private Date birthDay;
    private String address;
    private Prefix prefix;
    private int phoneNum;
    private Administrator administrator;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result
                + ((administrator == null) ? 0 : administrator.hashCode());
        result = prime * result
                + ((birthDay == null) ? 0 : birthDay.hashCode());
        result = prime * result
                + ((passportId == null) ? 0 : passportId.hashCode());
        result = prime * result + phoneNum;
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Subscriber other = (Subscriber) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (administrator == null) {
            if (other.administrator != null)
                return false;
        } else if (!administrator.equals(other.administrator))
            return false;
        if (birthDay == null) {
            if (other.birthDay != null)
                return false;
        } else if (!birthDay.equals(other.birthDay))
            return false;
        if (passportId == null) {
            if (other.passportId != null)
                return false;
        } else if (!passportId.equals(other.passportId))
            return false;
        if (phoneNum != other.phoneNum)
            return false;
        if (prefix == null) {
            if (other.prefix != null)
                return false;
        } else if (!prefix.equals(other.prefix))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Subscriber [user=" + user + ", passportId=" + passportId
                + ", birthDay=" + birthDay + ", address=" + address
                + ", prefix=" + prefix + ", phoneNum=" + phoneNum
                + ", administrator=" + administrator + ", id=" + id + "]";
    }
}
