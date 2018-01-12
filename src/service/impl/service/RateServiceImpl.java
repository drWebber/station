package service.impl.service;

import java.util.List;

import dao.interfaces.service.RateDao;
import domain.service.Rate;
import domain.service.RateType;
import exception.DaoException;
import exception.ServiceException;
import service.impl.TransactionService;
import service.interfaces.service.RateService;

public class RateServiceImpl extends TransactionService
        implements RateService {
    private RateDao rateDao;

    public RateDao getRateDao() {
        return rateDao;
    }

    public void setRateDao(RateDao rateDao) {
        this.rateDao = rateDao;
    }
    
    @Override
    public Rate getById(Long id) throws ServiceException {
        try {
            return rateDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rate getCurrentByType(RateType rateType)
            throws ServiceException {
        try {
            return rateDao.readCurrentByType(rateType);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Rate> getCurrentRates() throws ServiceException {
        try {
            return rateDao.readCurrentRates();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Rate entity) throws ServiceException {
        try {
            rateDao.create(entity);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
