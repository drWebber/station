package service.impl.payment;

import java.util.List;

import dao.interfaces.payment.InvoiceDao;
import dao.interfaces.service.CallsDetailDao;
import dao.interfaces.service.SubscriptionsDetailDao;
import domain.payment.Invoice;
import domain.service.CallsDetail;
import domain.service.SubscriptionsDetail;
import domain.user.Subscriber;
import exception.DaoException;
import exception.ServiceException;
import exception.TransactionException;
import service.impl.TransactionService;
import service.interfaces.payment.InvoiceService;

public class InvoiceServiceImpl extends TransactionService
        implements InvoiceService {
    private InvoiceDao invoiceDao;
    private CallsDetailDao callsDetailsDao;
    private SubscriptionsDetailDao subscriptionsDetailDao;

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public CallsDetailDao getCallsDetailsDao() {
        return callsDetailsDao;
    }

    public void setCallsDetailsDao(CallsDetailDao callsDetailsDao) {
        this.callsDetailsDao = callsDetailsDao;
    }

    public SubscriptionsDetailDao getSubscriptionsDetailDao() {
        return subscriptionsDetailDao;
    }

    public void setSubscriptionsDetailDao(
            SubscriptionsDetailDao subscriptionsDetailDao) {
        this.subscriptionsDetailDao = subscriptionsDetailDao;
    }

    @Override
    public void createInvoices() throws ServiceException {
        try {
            invoiceDao.createInvoices();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public Invoice getById(Long id) throws ServiceException {
        try {
            return invoiceDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Invoice invoice) throws ServiceException {
        try {
            if(invoice.getId() != null) {
                invoiceDao.update(invoice);
            } else {
                invoiceDao.create(invoice);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            invoiceDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            invoiceDao.deleteAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canCreate() throws ServiceException {
        try {
            return invoiceDao.canCreate();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Invoice> getUnpaid() throws ServiceException {
        try {
            return invoiceDao.readUnpaid();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Invoice> getInvoices(Subscriber subscriber,
            boolean isPaid) throws ServiceException {
        try {
            return invoiceDao.readInvoices(subscriber, isPaid);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Invoice getWithDetails(Long id, Subscriber subscriber)
            throws ServiceException {
        try {
            getTransaction().start();
            List<CallsDetail>callsDetail =
                    callsDetailsDao.getBySubscriber(subscriber);
            SubscriptionsDetail subscriptionsDetail =
                    subscriptionsDetailDao.getBySubscriber(subscriber);
            Invoice invoice = invoiceDao.read(id);
            invoice.setCallsDetail(callsDetail);
            invoice.setSubscriptionsDetail(subscriptionsDetail);
            getTransaction().commit();
            return invoice;
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }
}
