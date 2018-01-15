package service.impl.user;

import java.util.List;

import service.impl.TransactionService;
import service.interfaces.user.SubscriberService;
import dao.interfaces.user.AdministratorDao;
import dao.interfaces.user.SubscriberDao;
import dao.interfaces.user.UserDao;
import domain.user.Administrator;
import domain.user.Subscriber;
import domain.user.User;
import exception.DaoException;
import exception.LoginIsNotUnique;
import exception.PassportIdIsNotUnique;
import exception.PhoneIsNotUnique;
import exception.ServiceException;
import exception.TransactionException;
import exception.UserIsBannedException;

public class SubscriberServiceImpl extends TransactionService
        implements SubscriberService {
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
            getTransaction().start();
            Subscriber subscriber = subscriberDao.read(id);
            if (subscriber != null) {
                subscriber.setUser(userDao.read(id));
                Long adminId = subscriber.getAdministrator().getId();
                Administrator admin = administratorDao.read(adminId);
                admin.setUser(userDao.read(adminId));
                subscriber.setAdministrator(admin);
            }
            getTransaction().commit();
            return subscriber;
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public Subscriber getByLoginAndPassword(final String login,
            final String password)
            throws ServiceException {
        try {
            getTransaction().start();
            Subscriber subscriber = null;
            User user = userDao.readByLoginAndPassword(login, password);
            if (user != null) {
                subscriber = subscriberDao.read(user.getId());
                if (subscriber != null) {
                    subscriber.setUser(user);
                }
            }
            getTransaction().commit();
            return subscriber;
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Subscriber> getAll() throws ServiceException {
        try {
            getTransaction().start();
            List<Subscriber> subscribers = subscriberDao.readAll();
            for (Subscriber subscriber : subscribers) {
                subscriber.setUser(userDao.read(subscriber.getId()));
            }
            getTransaction().commit();
            return subscribers;
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Subscriber subscriber)
            throws ServiceException, LoginIsNotUnique, 
                PhoneIsNotUnique, PassportIdIsNotUnique {
        try {
            getTransaction().start();
            if (!subscriberDao.isPassportIdUnique(subscriber)) {
                throw new PassportIdIsNotUnique();
            }
            if (!userDao.isPhoneUnique(subscriber)) {
                throw new PhoneIsNotUnique();
            }
            if (subscriber.getId() != null) {
                userDao.update(subscriber.getUser());
                subscriberDao.update(subscriber);
            } else {
                if (!userDao.isLoginUnique(subscriber.getLogin())) {
                    throw new LoginIsNotUnique();
                }
                Long id = userDao.create(subscriber.getUser());
                subscriber.setId(id);
                subscriberDao.create(subscriber);
            }
            getTransaction().commit();
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            getTransaction().start();
            subscriberDao.delete(id);
            userDao.delete(id);
            getTransaction().commit();
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public void banById(Long id) throws ServiceException {
        try {
            userDao.banById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isBanned(final Long id)
            throws ServiceException, UserIsBannedException {
        try {
            boolean isBanned = userDao.isBanned(id);
            if (isBanned) {
                throw new UserIsBannedException("You are banned. "
                        + "Calling is restricted");
            }
            return isBanned;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
