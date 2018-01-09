package service.interfaces.payment;

import java.util.List;

import domain.payment.Invoice;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface InvoiceService extends CompleteService<Long, Invoice> {
    void createInvoices() throws ServiceException;

    boolean canCreate() throws ServiceException;

    /* метод используется в целях демонстрации работоспособности
     * механизма выставления счетов абонентам (выставляются раз
     * в месяц)
     */
    void deleteAll() throws ServiceException;

    List<Invoice> getUnpaid() throws ServiceException;
}
