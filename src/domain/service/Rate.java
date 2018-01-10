package domain.service;

import java.sql.Timestamp;

import domain.Entity;

public class Rate extends Entity<Long> {
    private static final long serialVersionUID = 1L;
    private RateType type;
    private Timestamp introdutionDate;
    private Float tariff;
    
    public RateType getType() {
        return type;
    }
    
    public void setType(RateType type) {
        this.type = type;
    }
    
    public Timestamp getIntrodutionDate() {
        return introdutionDate;
    }
    
    public void setIntrodutionDate(Timestamp introdutionDate) {
        this.introdutionDate = introdutionDate;
    }
    
    public Float getTariff() {
        return tariff;
    }
    
    public void setTariff(Float tariff) {
        this.tariff = tariff;
    }
    
    @Override
    public String toString() {
        return "Rate [type=" + type + ", introdutionDate=" + introdutionDate
                + ", tariff=" + tariff + ", id=" + id + "]";
    }
}
