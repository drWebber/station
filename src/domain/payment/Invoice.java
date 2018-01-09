package domain.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;

import domain.Entity;
import domain.user.Subscriber;

public class Invoice extends Entity<Long> {
    private Subscriber subscriber;
    private Timestamp invoicingDate;
    private BigDecimal amount;
    
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice [subscriber=" + subscriber + ", invoicingDate="
                + invoicingDate + ", amount=" + amount + ", id=" + id + "]";
    }
}
