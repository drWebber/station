package dao.interfaces.service;

import java.util.List;

import dao.UpdatableDao;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.DaoException;

public interface SubscriptionDao extends UpdatableDao<Long, Subscription> {
    List<Subscription> readSubscriptions(Subscriber subscriber,
            boolean readArchieved) throws DaoException;

    boolean isSubscribed(Subscription subscription) throws DaoException;
}
