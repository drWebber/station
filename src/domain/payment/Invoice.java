package domain.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import domain.Entity;
import domain.service.CallsDetail;
import domain.service.SubscriptionsDetail;
import domain.user.Subscriber;

public class Invoice extends Entity<Long> {
    private static final long serialVersionUID = 4L;
    private Subscriber subscriber;
    private Timestamp invoicingDate;
    private BigDecimal amount;
    private List<CallsDetail> callsDetail;
    private SubscriptionsDetail subscriptionsDetail;
    
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
    
    public List<CallsDetail> getCallsDetail() {
        return callsDetail;
    }
    
    public void setCallsDetail(List<CallsDetail> callsDetail) {
        this.callsDetail = callsDetail;
    }

    public SubscriptionsDetail getSubscriptionsDetail() {
        return subscriptionsDetail;
    }

    public void setSubscriptionsDetail(
            SubscriptionsDetail subscriptionsDetail) {
        this.subscriptionsDetail = subscriptionsDetail;
    }

    @Override
    public String toString() {
        return "Invoice [subscriber=" + subscriber + ", invoicingDate="
                + invoicingDate + ", amount=" + amount + ", id=" + id + "]";
    }
}
