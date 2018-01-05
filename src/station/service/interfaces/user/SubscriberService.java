package station.service.interfaces.user;

import java.util.List;

import station.domain.user.Subscriber;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.CompleteService;

public interface SubscriberService extends CompleteService<Long, Subscriber> {
    List<Subscriber> getAll() throws ServiceException;

    Subscriber getByLoginAndPassword(String login, String password)
            throws DaoException;
}
