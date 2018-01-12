package service.interfaces.payment;

import java.util.List;

import domain.payment.Payment;
import domain.user.Subscriber;
import exception.ServiceException;
import service.interfaces.BaseService;

public interface PaymentService extends BaseService<Long, Payment> {
    
    List<Payment> getBySubscriber(Subscriber subscriber)
            throws ServiceException;
    
    /* Used only for testing. Not for real system */
    void deleteAll() throws ServiceException;
}
