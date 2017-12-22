package station.domain.services;

import java.sql.Timestamp;

import station.domain.Entity;
import station.domain.user.Subscriber;

public class Service extends Entity<Long> {
    private Subscriber subscriber;
    private ProvidedService service;
    private Timestamp connected;
    private Timestamp disconnected;
    
    public Service() { }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public ProvidedService getService() {
        return service;
    }

    public void setService(ProvidedService service) {
        this.service = service;
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
        return "Service [subscriber=" + subscriber + ", service=" + service
                + ", connected=" + connected + ", disconnected=" + disconnected
                + ", id=" + id + "]";
    }
}
