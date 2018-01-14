package service.interfaces.service;

import java.util.List;

import domain.service.Offer;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface OfferService extends CompleteService<Integer, Offer> {
    List<Offer> getAll() throws ServiceException;

    List<Offer> getByRequirement(boolean require)
            throws ServiceException;
}
