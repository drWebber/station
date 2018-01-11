package domain.service;

import java.math.BigDecimal;

import domain.user.Subscriber;

public class SubscriptionsDetail {
    private Subscriber subscriber;
    private BigDecimal majorFee;
    private BigDecimal fee;
    private BigDecimal subscriptionsCost;
    
    public Subscriber getSubscriber() {
        return subscriber;
    }
    
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
    
    public BigDecimal getMajorFee() {
        return majorFee;
    }
    
    public void setMajorFee(BigDecimal majorFee) {
        this.majorFee = majorFee;
    }
    
    public BigDecimal getFee() {
        return fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    
    public BigDecimal getSubscriptionsCost() {
        return subscriptionsCost;
    }
    
    public void setSubscriptionsCost(BigDecimal subscriptionsCost) {
        this.subscriptionsCost = subscriptionsCost;
    }
}
