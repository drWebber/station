package service.interfaces.payment;

import java.util.List;

import domain.payment.Payment;
import domain.user.Subscriber;
import exception.ServiceException;
import service.interfaces.BaseService;

public interface PaymentService extends BaseService<Long, Payment> {
    
    List<Payment> getBySubscriber(Subscriber subscriber)
            throws ServiceException;
    
    /* метод используется в целях тестирования работоспособности
     * механизма выставления счетов абонентам (выставляются раз
     * в месяц), исходный дао слой предполагает наличие только
     * CR операций
     */
    void deleteAll() throws ServiceException;
}
