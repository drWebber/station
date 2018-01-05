package domain.user;

import domain.Entity;

public class Administrator extends Entity<Long> {
    private User user;
    private int personalId;
    private String position;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + personalId;
        result = prime * result
                + ((position == null) ? 0 : position.hashCode());
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
        Administrator other = (Administrator) obj;
        if (personalId != other.personalId)
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Administrator [user=" + user + ", personalId=" + personalId
                + ", position=" + position + ", id=" + id + "]";
    }
}
