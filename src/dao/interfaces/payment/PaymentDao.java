package dao.interfaces.payment;

import java.util.List;

import dao.BaseDao;
import domain.payment.Payment;
import domain.user.Subscriber;
import exception.DaoException;

public interface PaymentDao extends BaseDao<Long, Payment> {
    List<Payment> readBySubscriber(Subscriber subscriber) throws DaoException;

    /* Used only for testing. Not for real system */
    void deleteAll() throws DaoException;
}
