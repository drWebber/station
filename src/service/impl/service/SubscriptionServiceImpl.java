package service.impl.service;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.OfferDao;
import dao.interfaces.service.SubscriptionDao;
import domain.service.Offer;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.service.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDao subscriptionDao;
    private OfferDao OfferDao;

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
    
    @Override
    public Subscription getById(Long id) throws ServiceException {
        try {
            return subscriptionDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Subscription> getSubscriptions(Subscriber subscriber)
            throws ServiceException {
        try {
            List<Subscription> services = new ArrayList<>();
            services = subscriptionDao.readSubscriberServices(subscriber);
            for (Subscription service : services) {
                Offer providedSrv = 
                        OfferDao.read(service.getOffer().getId());
                service.setOffer(providedSrv);
            }
            return services;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Subscription subscription) throws ServiceException {
        try {
            if(subscription.getId() != null) {
                subscriptionDao.update(subscription);
            } else {
                subscriptionDao.create(subscription);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
