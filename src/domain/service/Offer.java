package domain.service;

import domain.NamedEntity;

public class Offer extends NamedEntity<Integer> {
    private static final long serialVersionUID = 4L;
    private String description;
    private Float monthlyFee;
    private Float subscriptionRate;
    private boolean required;
    private boolean isSubscribed = false;

    public Offer() { }

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
    
    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Offer other = (Offer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Offer [description=" + description + ", monthlyFee="
                + monthlyFee + ", subscriptionRate=" + subscriptionRate
                + ", required=" + required + ", isSubscribed=" + isSubscribed
                + ", name=" + name + ", id=" + id + "]";
    }
}
