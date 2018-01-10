package dao.interfaces.payment;

import dao.BaseDao;
import domain.payment.Payment;
import exception.DaoException;

public interface PaymentDao extends BaseDao<Long, Payment> {
    /* метод используется в целях тестирования работоспособности
     * механизма выставления счетов абонентам (выставляются раз
     * в месяц), исходный дао слой предполагает наличие только
     * CR операций
     */
    void deleteAll() throws DaoException;
}
