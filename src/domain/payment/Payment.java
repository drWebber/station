package domain.payment;

import java.sql.Timestamp;

import domain.Entity;

public class Payment extends Entity<Long> {
    private static final long serialVersionUID = 3L;
    private Timestamp date;
    private Invoice invoice = new Invoice();
    
    public Payment() { }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment [date=" + date + ", invoice=" + invoice + ", id=" + id
                + "]";
    }
}
