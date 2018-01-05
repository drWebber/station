package dao.interfaces.user;

import java.util.List;

import dao.CompleteDao;
import domain.user.Subscriber;
import exception.DaoException;

public interface SubscriberDao extends CompleteDao<Long, Subscriber> {
    List<Subscriber> readAll() throws DaoException;
}
