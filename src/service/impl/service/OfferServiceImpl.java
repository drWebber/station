package service.impl.service;

import java.util.List;

import dao.interfaces.service.OfferDao;
import domain.service.Offer;
import exception.DaoException;
import exception.ServiceException;
import service.impl.TransactionService;
import service.interfaces.service.OfferService;

public class OfferServiceImpl extends TransactionService
        implements OfferService {
    private OfferDao offerDao;
    
    public OfferDao getOfferDao() {
        return offerDao;
    }

    public void setOfferDao(OfferDao providedSrvDao) {
        this.offerDao = providedSrvDao;
    }

    @Override
    public Offer getById(Integer id) throws ServiceException {
        try {
            return offerDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Offer> getAll() throws ServiceException {
        try {
            return offerDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Offer> getByRequirement(boolean require) 
            throws ServiceException {
        try {
            return offerDao.readByRequirement(require);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Offer service) throws ServiceException {
        try {
            if(service.getId() != null) {
                offerDao.update(service);
            } else {
                offerDao.create(service);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            offerDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
