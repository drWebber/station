package station.service.impl.payment;

import station.dao.interfaces.payment.PaymentDao;
import station.domain.payment.Payment;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.payment.PaymentService;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDao paymentDao;

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
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
}
