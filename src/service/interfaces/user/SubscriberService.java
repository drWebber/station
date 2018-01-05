package service.interfaces.user;

import java.util.List;

import domain.user.Subscriber;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface SubscriberService extends CompleteService<Long, Subscriber> {
    List<Subscriber> getAll() throws ServiceException;

    Subscriber getByLoginAndPassword(String login, String password)
            throws DaoException;
}
