package service.interfaces.service;

import java.util.List;

import service.interfaces.BaseService;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.ServiceException;
import exception.UserIsBannedException;

public interface SubscriptionService extends BaseService<Long, Subscription> {
    List<Subscription> getSubscriptions(Subscriber subscriber,
            boolean readArchieved) throws ServiceException;

    void validateAndSave(Subscription subscription) throws ServiceException,
            UserIsBannedException;
}
