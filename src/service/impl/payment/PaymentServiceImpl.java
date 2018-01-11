package service.impl.payment;

import java.util.List;

import dao.interfaces.payment.InvoiceDao;
import dao.interfaces.payment.PaymentDao;
import domain.payment.Invoice;
import domain.payment.Payment;
import domain.user.Subscriber;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.payment.PaymentService;

public class PaymentServiceImpl implements PaymentService {
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
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Payment payment) throws ServiceException {
        try {
            if(payment.getId() != null) {
                throw new ServiceException("The data-modification is "
                        + "restricted");
            } else {
                paymentDao.create(payment);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            paymentDao.deleteAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Payment> getBySubscriber(Subscriber subscriber)
            throws ServiceException {
        try {
            List<Payment> payments = paymentDao.readBySubscriber(subscriber);
            for (Payment payment : payments) {
                Invoice invoice = invoiceDao.read(payment.getInvoice().getId());
                payment.setInvoice(invoice);
            }
            return payments;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
