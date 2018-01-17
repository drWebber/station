package service.interfaces.payment;

import java.util.List;

import domain.payment.Invoice;
import domain.user.Subscriber;
import exception.service.ServiceException;
import service.interfaces.CompleteService;

public interface InvoiceService extends CompleteService<Long, Invoice> {
    void createInvoices() throws ServiceException;

    boolean canCreate() throws ServiceException;

    List<Invoice> getUnpaid() throws ServiceException;

    List<Invoice> getInvoices(Subscriber subscriber, boolean isPaid)
            throws ServiceException;

    Invoice getWithDetails(Long id, Subscriber subscriber)
        throws ServiceException;

    /* Used only for testing. Not for real system */
    void deleteAll() throws ServiceException;
}
