package service.impl.service;

import java.util.ArrayList;
import java.util.List;

import service.impl.TransactionService;
import service.interfaces.service.SubscriptionService;
import dao.interfaces.service.OfferDao;
import dao.interfaces.service.SubscriptionDao;
import dao.interfaces.user.UserDao;
import domain.service.Offer;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.DaoException;
import exception.TransactionException;
import exception.service.OfferIsAlreadySubscribed;
import exception.service.ServiceException;
import exception.service.UserIsBannedException;

public class SubscriptionServiceImpl extends TransactionService
        implements SubscriptionService {
    private SubscriptionDao subscriptionDao;
    private OfferDao OfferDao;
    private UserDao userDao;

    public SubscriptionDao getServicesDao() {
        return subscriptionDao;
    }

    public void setServicesDao(SubscriptionDao servicesDao) {
        this.subscriptionDao = servicesDao;
    }

    public OfferDao getOfferDao() {
        return OfferDao;
    }

    public void setOfferDao(OfferDao offerDao) {
        this.OfferDao = offerDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Subscription getById(Long id) throws ServiceException {
        try {
            return subscriptionDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Subscription> getSubscriptions(Subscriber subscriber, 
            boolean readArchieved) throws ServiceException {
        try {
            getTransaction().start();
            List<Subscription> services = new ArrayList<>();
            services = subscriptionDao.readSubscriptions(subscriber,
                    readArchieved);
            for (Subscription service : services) {
                Offer providedSrv =
                        OfferDao.read(service.getOffer().getId());
                service.setOffer(providedSrv);
            }
            getTransaction().commit();
            return services;
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Subscription subscription) throws ServiceException,
            UserIsBannedException, OfferIsAlreadySubscribed {
        try {
            getTransaction().start();
            if (subscription.getId() != null) {
                subscriptionDao.update(subscription);
                getTransaction().commit();
            } else {
                boolean isBanned =
                        userDao.isBanned(subscription.getSubscriber().getId());
                boolean isSubscribed =
                        subscriptionDao.isSubscribed(subscription);
                subscriptionDao.create(subscription);
                if (isBanned) {
                    throw new UserIsBannedException(" Subscriptions "
                            + "is resctricted");
                }
                if (isSubscribed) {
                    throw new OfferIsAlreadySubscribed();
                }
                getTransaction().commit();
            }
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }
}
