package station.service.impl.service;

import station.dao.interfaces.service.CallingRateDao;
import station.domain.service.CallingRate;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.service.CallingRateService;

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
            CallingRate rate = rateDao.read(id);
            return rate;
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
