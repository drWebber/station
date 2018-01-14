package dao.interfaces.service;

import java.util.List;

import dao.CompleteDao;
import domain.service.Offer;
import exception.DaoException;

public interface OfferDao extends CompleteDao<Integer, Offer> {
    List<Offer> readAll() throws DaoException;
    List<Offer> readByRequirement(boolean require)
            throws DaoException;
}
