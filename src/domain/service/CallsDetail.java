package domain.service;


import domain.user.Subscriber;

public class CallsDetail {
    private Subscriber subscriber;
    private RateType rateType;
    private int duration;
    private float tariff;
    
    public Subscriber getSubscriber() {
        return subscriber;
    }
    
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
    
    public RateType getRateType() {
        return rateType;
    }
    
    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public float getTariff() {
        return tariff;
    }
    
    public void setTariff(float tariff) {
        this.tariff = tariff;
    }
    
    public String getFormattedDuration() {
        int hr = duration/3600;
        int rem = duration%3600;
        int min = rem/60;
        int sec = rem%60;
        String HH = (hr < 10 ? "0" : "")+hr;
        String mm = (min < 10 ? "0" : "")+min;
        String ss = (sec<10 ? "0" : "")+sec; 
        return HH + ":" + mm + ":" + ss;
    }
}
