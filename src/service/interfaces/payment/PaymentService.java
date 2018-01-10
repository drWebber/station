package service.interfaces.payment;

import domain.payment.Payment;
import exception.ServiceException;
import service.interfaces.BaseService;

public interface PaymentService extends BaseService<Long, Payment> {
    /* метод используется в целях тестирования работоспособности
     * механизма выставления счетов абонентам (выставляются раз
     * в месяц), исходный дао слой предполагает наличие только
     * CR операций
     */
    void deleteAll() throws ServiceException;
}
