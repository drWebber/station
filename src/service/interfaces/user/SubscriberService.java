package service.interfaces.user;

import java.util.List;

import domain.user.Subscriber;
import exception.LoginIsNotUnique;
import exception.PassportIdIsNotUnique;
import exception.PhoneIsNotUnique;
import exception.ServiceException;
import exception.UserIsBannedException;

public interface SubscriberService {
    Subscriber getById(Long id) throws ServiceException;
    
    void save(Subscriber subscriber)
            throws ServiceException, LoginIsNotUnique,
                PhoneIsNotUnique, PassportIdIsNotUnique;
    
    void delete(Long id) throws ServiceException;
    
    List<Subscriber> getAll() throws ServiceException;

    Subscriber getByLoginAndPassword(String login, String password)
            throws ServiceException;

    void banById(Long id) throws ServiceException;

    boolean isBanned(Long id) throws ServiceException, UserIsBannedException;
}
