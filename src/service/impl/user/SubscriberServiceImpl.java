package service.impl.user;

import java.util.List;

import dao.interfaces.user.AdministratorDao;
import dao.interfaces.user.SubscriberDao;
import dao.interfaces.user.UserDao;
import domain.user.Administrator;
import domain.user.Subscriber;
import domain.user.User;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.user.SubscriberService;

public class SubscriberServiceImpl implements SubscriberService {
    private UserDao userDao;
    private SubscriberDao subscriberDao;
    private AdministratorDao administratorDao;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSubscriberDao(SubscriberDao subscriberDao) {
        this.subscriberDao = subscriberDao;
    }

    public AdministratorDao getAdministratorDao() {
        return administratorDao;
    }

    public void setAdministratorDao(AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

    @Override
    public Subscriber getById(Long id) throws ServiceException {
        try {
            Subscriber subscriber = subscriberDao.read(id);
            if (subscriber != null) {
                subscriber.setUser(userDao.read(id));
                Long adminId = subscriber.getAdministrator().getId();
                Administrator admin = administratorDao.read(adminId);
                admin.setUser(userDao.read(adminId));
                subscriber.setAdministrator(admin);
            }
            return subscriber;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Subscriber getByLoginAndPassword(String login, String password)
            throws ServiceException {
        try {
            Subscriber subscriber = null;
            User user = userDao.readByLoginAndPassword(login, password);
            if (user != null) {
                subscriber = subscriberDao.read(user.getId());
                if (subscriber != null) {
                    subscriber.setUser(user);
                }
            }
            return subscriber;
        } catch (DaoException e) {
            throw new ServiceException(e);
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

    @Override
    public void banById(Long id) throws ServiceException {
        try {
            userDao.banById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
