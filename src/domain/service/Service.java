package domain.service;

import java.sql.Timestamp;

import domain.Entity;
import domain.user.Subscriber;

public class Service extends Entity<Long> {
    private Subscriber subscriber;
    private ProvidedService providedService;
    private Timestamp connected;
    private Timestamp disconnected;
    
    public Service() { }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public ProvidedService getProvidedService() {
        return providedService;
    }

    public void setProvidedService(ProvidedService service) {
        this.providedService = service;
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
        return "Service [subscriber=" + subscriber + ", service=" + providedService
                + ", connected=" + connected + ", disconnected=" + disconnected
                + ", id=" + id + "]";
    }
}
