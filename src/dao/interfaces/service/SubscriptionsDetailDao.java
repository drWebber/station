package dao.interfaces.service;

import domain.service.SubscriptionsDetail;
import domain.user.Subscriber;
import exception.DaoException;

public interface SubscriptionsDetailDao {
    SubscriptionsDetail getBySubscriber(Subscriber subscriber)
            throws DaoException;
}
