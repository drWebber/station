package domain.user;

import domain.Entity;

public class Prefix extends Entity<Integer> {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Prefix [city=" + city + ", id=" + id + "]";
    }
}
