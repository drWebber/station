package dao.interfaces.payment;

import java.util.List;

import dao.BaseDao;
import domain.payment.Payment;
import domain.user.Subscriber;
import exception.DaoException;

public interface PaymentDao extends BaseDao<Long, Payment> {
    List<Payment> readBySubscriber(Subscriber subscriber) throws DaoException;
    
    /* метод используется в целях тестирования работоспособности
     * механизма выставления счетов абонентам (выставляются раз
     * в месяц), исходный дао слой предполагает наличие только
     * CR операций
     */
    void deleteAll() throws DaoException;
}
