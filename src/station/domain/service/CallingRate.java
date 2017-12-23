package station.domain.service;

import station.domain.NamedEntity;

public class CallingRate extends NamedEntity<Short> {
    private Float rate;
    
    public CallingRate() { }
    
    public Float getRate() {
        return rate;
    }
    
    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CallingRate [rate=" + rate + ", name=" + name + ", id=" + id
                + "]";
    }
}
