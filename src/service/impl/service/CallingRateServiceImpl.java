package service.impl.service;

import dao.interfaces.service.CallingRateDao;
import domain.service.CallingRate;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.service.CallingRateService;

public class CallingRateServiceImpl implements CallingRateService {
    private CallingRateDao rateDao;

    public CallingRateDao getRateDao() {
        return rateDao;
    }

    public void setRateDao(CallingRateDao rateDao) {
        this.rateDao = rateDao;
    }
    
    @Override
    public CallingRate getById(Short id) throws ServiceException {
        try {
            return rateDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(CallingRate rate) throws ServiceException {
        try {
            if(rate.getId() != null) {
                rateDao.update(rate);
            } else {
                rateDao.create(rate);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Short id) throws ServiceException {
        try {
            rateDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
