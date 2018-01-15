package dao.interfaces.service;

import java.util.List;

import dao.CompleteDao;
import domain.service.Offer;
import domain.user.Subscriber;
import exception.DaoException;

public interface OfferDao extends CompleteDao<Integer, Offer> {
    List<Offer> readAll() throws DaoException;
    
    List<Offer> readByRequirement(final boolean require)
            throws DaoException;

    List<Offer> readSubscribedOffers(final Subscriber subscriber)
        throws DaoException;
}
