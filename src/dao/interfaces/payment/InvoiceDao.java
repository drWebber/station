package dao.interfaces.payment;

import java.util.List;

import dao.CompleteDao;
import domain.payment.Invoice;
import domain.user.Subscriber;
import exception.DaoException;

public interface InvoiceDao extends CompleteDao<Long, Invoice> {
    void createInvoices() throws DaoException;

    boolean canCreate() throws DaoException;

    List<Invoice> readUnpaid() throws DaoException;

    List<Invoice> readInvoices(Subscriber subscriber,
            boolean isPaid) throws DaoException;

    /* Used only for testing. Not for real system */
    void deleteAll() throws DaoException;
}
