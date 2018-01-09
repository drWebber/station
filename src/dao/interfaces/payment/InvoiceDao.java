package dao.interfaces.payment;

import java.util.List;

import dao.CompleteDao;
import domain.payment.Invoice;
import exception.DaoException;

public interface InvoiceDao extends CompleteDao<Long, Invoice> {
    void createInvoices() throws DaoException;
    
    boolean canCreate() throws DaoException;

    /* метод используется в целях демонстрации работоспособности
     * механизма выставления счетов абонентам (выставляются раз
     * в месяц)
     */
    void deleteAll() throws DaoException;

    List<Invoice> readUnpaid() throws DaoException;
}
