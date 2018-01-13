package util.service;

import domain.service.RateType;
import domain.user.Subscriber;

public class CallRateResolver {
    private Subscriber subscriber;
    private Subscriber opponent;
    private CallDirection direction;
    
    public CallRateResolver(Subscriber subscriber, Subscriber opponent,
            CallDirection direction) {
        this.subscriber = subscriber;
        this.opponent = opponent;
        this.direction = direction;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Subscriber getOpponent() {
        return opponent;
    }

    public void setOpponent(Subscriber opponent) {
        this.opponent = opponent;
    }

    public CallDirection getDirection() {
        return direction;
    }

    public void setDirection(CallDirection direction) {
        this.direction = direction;
    }

    public RateType getResolvedRate() {
        if (subscriber.getPrefix().getId().equals(
                opponent.getPrefix().getId())) {
            return direction == CallDirection.INCOMING
                                ? RateType.LOCAL_INCOMING
                                : RateType.LOCAL_OUTGOING;
        } else if (MobilePrefix.valueOf(opponent.getPrefix().getId()) != null) {
            return direction == CallDirection.INCOMING
                                ? RateType.MOBILE_INCOMING
                                : RateType.MOBILE_OUTGOING;
        } else {
            return direction == CallDirection.INCOMING
                                ? RateType.LONG_DISTANCE_INCOMING
                                : RateType.LONG_DISTANCE_OUTGOING;
        }
    }
}
