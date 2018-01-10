package domain.payment;

import java.sql.Timestamp;

import domain.Entity;

public class Payment extends Entity<Long> {
    private static final long serialVersionUID = 1L;
    private Timestamp date;
    private String bankCode;
    private Invoice invoice;
    
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String toString() {
        return "Payment [date=" + date + ", bankCode=" + bankCode
                + ", invoice=" + invoice + ", id=" + id + "]";
    }
}
