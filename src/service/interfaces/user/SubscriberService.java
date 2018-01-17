package service.interfaces.user;

import java.util.List;

import service.interfaces.CompleteService;
import domain.user.Subscriber;
import exception.service.ServiceException;
import exception.service.UserIsBannedException;

public interface SubscriberService extends CompleteService<Long, Subscriber> {
    List<Subscriber> getAll() throws ServiceException;

    Subscriber getByLoginAndPassword(String login, String password)
            throws ServiceException;

    void banById(Long id) throws ServiceException;

    boolean isBanned(Long id) throws ServiceException, UserIsBannedException;
}
