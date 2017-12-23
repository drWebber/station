package station.domain.payment;

import java.sql.Timestamp;
import station.domain.Entity;
import station.domain.user.Subscriber;

public class Invoice extends Entity<Long> {
    private Subscriber subscriber;
    private Timestamp invoicingDate;
    private Float amount;
    
    public Invoice() { }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Timestamp getInvoicingDate() {
        return invoicingDate;
    }

    public void setInvoicingDate(Timestamp invoicingDate) {
        this.invoicingDate = invoicingDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice [subscriber=" + subscriber + ", invoicingDate="
                + invoicingDate + ", amount=" + amount + ", id=" + id + "]";
    }
}
