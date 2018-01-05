package service.impl.user;

import java.util.List;

import dao.interfaces.user.SubscriberDao;
import dao.interfaces.user.UserDao;
import domain.user.Subscriber;
import domain.user.User;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.user.SubscriberService;

public class SubscriberServiceImpl implements SubscriberService {
    private UserDao userDao;
    private SubscriberDao subscriberDao;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSubscriberDao(SubscriberDao subscriberDao) {
        this.subscriberDao = subscriberDao;
    }

    @Override
    public Subscriber getById(Long id) throws ServiceException {
        try {
            Subscriber subscriber = subscriberDao.read(id);
            if (subscriber != null) {
                subscriber.setUser(userDao.read(id));
            }
            return subscriber;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Subscriber getByLoginAndPassword(String login, String password)
            throws DaoException {
        try {
            Subscriber subscriber = null;
            User user = userDao.readByLoginAndPassword(login, password);
            System.out.println(user);
            if (user != null) {
                subscriber = subscriberDao.read(user.getId());
                subscriber.setUser(user);
            }
            return subscriber;
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Subscriber> getAll() throws ServiceException {
        try {
            List<Subscriber> subscribers = subscriberDao.readAll();
            for (Subscriber subscriber : subscribers) {
                subscriber.setUser(userDao.read(subscriber.getId()));
            }
            return subscribers;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Subscriber subscriber) throws ServiceException {
        try {
            if(subscriber.getId() != null) {
                userDao.update(subscriber.getUser());
                subscriberDao.update(subscriber);
            } else {
                Long id = userDao.create(subscriber.getUser());
                subscriber.setId(id);
                subscriberDao.create(subscriber);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            subscriberDao.delete(id);
            userDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
