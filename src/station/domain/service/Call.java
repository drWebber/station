package station.domain.service;

import java.sql.Timestamp;

import station.domain.Entity;
import station.domain.user.Subscriber;

public class Call extends Entity<Long> {
    private Subscriber subscriber;
    private Integer phoneNum;
    private Timestamp beginTime;
    private Timestamp finishTime;
    private CallingRate rate;
    
    public Call() { }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
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

    public void setBeginTime(Timestamp begin) {
        this.beginTime = begin;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finish) {
        this.finishTime = finish;
    }

    public CallingRate getRate() {
        return rate;
    }

    public void setRate(CallingRate rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Call [subscriber=" + subscriber + ", phoneNum=" + phoneNum
                + ", beginTime=" + beginTime + ", finishTime=" + finishTime
                + ", rate=" + rate + ", id=" + id + "]";
    }
}
