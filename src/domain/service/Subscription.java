package domain.service;

import java.sql.Timestamp;

import domain.Entity;
import domain.user.Subscriber;

public class Subscription extends Entity<Long> {
    private Subscriber subscriber;
    private Offer offer;
    private Timestamp connected;
    private Timestamp disconnected;
    
    public Subscription() { }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Timestamp getConnected() {
        return connected;
    }

    public void setConnected(Timestamp connected) {
        this.connected = connected;
    }

    public Timestamp getDisconnected() {
        return disconnected;
    }

    public void setDisconnected(Timestamp disconnected) {
        this.disconnected = disconnected;
    }

    @Override
    public String toString() {
        return "Service [subscriber=" + subscriber + ", service=" + offer
                + ", connected=" + connected + ", disconnected=" + disconnected
                + ", id=" + id + "]";
    }
}
