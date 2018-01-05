package domain.service;

import domain.NamedEntity;

public class ProvidedService extends NamedEntity<Integer> {
    private String description;
    private Float monthlyFee;
    private Float subscriptionRate;
    private boolean required;
    
    public ProvidedService() { }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Float getSubscriptionRate() {
        return subscriptionRate;
    }

    public void setSubscriptionRate(Float subscriptionRate) {
        this.subscriptionRate = subscriptionRate;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public String toString() {
        return "ProvidedService [description=" + description + ", monthlyFee="
                + monthlyFee + ", subscriptionRate=" + subscriptionRate
                + ", required=" + required + ", name=" + name + ", id=" + id
                + "]";
    }
}
