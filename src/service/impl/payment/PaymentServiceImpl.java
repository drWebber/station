package service.impl.payment;

import java.util.List;

import dao.interfaces.payment.InvoiceDao;
import dao.interfaces.payment.PaymentDao;
import domain.payment.Invoice;
import domain.payment.Payment;
import domain.user.Subscriber;
import exception.DaoException;
import exception.TransactionException;
import exception.service.InvoiceIsAlreadyBeenPaid;
import exception.service.ServiceException;
import service.impl.TransactionService;
import service.interfaces.payment.PaymentService;

public class PaymentServiceImpl extends TransactionService
        implements PaymentService {
    private PaymentDao paymentDao;
    private InvoiceDao invoiceDao;

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @Override
    public Payment getById(Long id) throws ServiceException {
        try {
            return paymentDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Payment payment)
            throws ServiceException, InvoiceIsAlreadyBeenPaid {
        try {
            getTransaction().start();
            if (payment.getId() != null) {
                throw new ServiceException("The data-modification is "
                        + "restricted");
            } else {
                if (paymentDao.isAlreadyPaid(payment)) {
                    throw new InvoiceIsAlreadyBeenPaid(
                            payment.getInvoice().getId());
                }
                paymentDao.create(payment);
            }
            getTransaction().commit();
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            paymentDao.deleteAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Payment> getBySubscriber(Subscriber subscriber)
            throws ServiceException {
        try {
            getTransaction().start();
            List<Payment> payments = paymentDao.readBySubscriber(subscriber);
            for (Payment payment : payments) {
                Invoice invoice = invoiceDao.read(payment.getInvoice().getId());
                payment.setInvoice(invoice);
            }
            getTransaction().commit();
            return payments;
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }
}
