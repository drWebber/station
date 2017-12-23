package station.service.impl.user;

import station.dao.interfaces.user.SubscriberDao;
import station.dao.interfaces.user.UserDao;
import station.domain.user.Subscriber;
import station.domain.user.User;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.user.SubscriberService;

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
            User user = userDao.read(id);
            Subscriber subscriber = subscriberDao.read(id);
            subscriber.setUser(user);
            return subscriber;
        } catch(DaoException e) {
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
