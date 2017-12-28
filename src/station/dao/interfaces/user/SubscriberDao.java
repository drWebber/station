package station.dao.interfaces.user;

import java.util.List;

import station.dao.CompleteDao;
import station.domain.user.Subscriber;
import station.exception.DaoException;

public interface SubscriberDao extends CompleteDao<Long, Subscriber> {
    List<Subscriber> readAll() throws DaoException;
}
