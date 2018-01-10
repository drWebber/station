package service.interfaces.payment;

import java.util.List;

import domain.payment.Invoice;
import domain.user.Subscriber;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface InvoiceService extends CompleteService<Long, Invoice> {
    void createInvoices() throws ServiceException;

    boolean canCreate() throws ServiceException;

    List<Invoice> getUnpaid() throws ServiceException;

    List<Invoice> getInvoices(Subscriber subscriber, boolean isPaid)
            throws ServiceException;

    /* метод используется в целях тестирования работоспособности
     * механизма выставления счетов абонентам (выставляются раз
     * в месяц)
     */
    void deleteAll() throws ServiceException;
}
