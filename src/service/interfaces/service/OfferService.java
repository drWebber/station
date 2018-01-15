package service.interfaces.service;

import java.util.List;

import service.interfaces.CompleteService;
import domain.service.Offer;
import domain.user.Subscriber;
import exception.ServiceException;

public interface OfferService extends CompleteService<Integer, Offer> {
    List<Offer> getAll() throws ServiceException;

    List<Offer> getByRequirement(final boolean require)
            throws ServiceException;
    
    List<Offer> getBySubscriber(final Subscriber subscriber)
            throws ServiceException;
}
