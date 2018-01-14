package domain.service;

import java.sql.Timestamp;

import domain.Entity;
import domain.user.Prefix;
import domain.user.Subscriber;

public class Call extends Entity<Long> {
    private static final long serialVersionUID = 5L;
    private Subscriber subscriber = new Subscriber();
    private Prefix prefix = new Prefix();
    private Integer phoneNum;
    private Timestamp beginTime;
    private Timestamp finishTime;
    private Rate rate = new Rate();
    private RateType rateType;

    public Subscriber getSubscriber() {
        return subscriber;
    }
    
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
    
    public Prefix getPrefix() {
        return prefix;
    }
    
    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }
    
    public Integer getPhoneNum() {
        return phoneNum;
    }
    
    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public Timestamp getBeginTime() {
        return beginTime;
    }
    
    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }
    
    public Timestamp getFinishTime() {
        return finishTime;
    }
    
    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }
    
    public Rate getRate() {
        return rate;
    }
    
    public void setRate(Rate rate) {
        this.rate = rate;
    }
    
    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }
    
    @Override
    public String toString() {
        return "Call [subscriber=" + subscriber + ", prefix=" + prefix
                + ", phoneNum=" + phoneNum + ", beginTime=" + beginTime
                + ", finishTime=" + finishTime + ", rate=" + rate + ", id="
                + id + "]";
    }
}
