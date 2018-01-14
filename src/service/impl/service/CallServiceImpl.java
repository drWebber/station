package service.impl.service;

import service.impl.TransactionService;
import service.interfaces.service.CallService;
import dao.interfaces.service.CallDao;
import dao.interfaces.service.RateDao;
import domain.service.Call;
import domain.service.Rate;
import exception.DaoException;
import exception.ServiceException;

public class CallServiceImpl extends TransactionService
        implements CallService {
    private CallDao callDao;
    private RateDao rateDao;

    public CallDao getCallDao() {
        return callDao;
    }

    public void setCallDao(CallDao callDao) {
        this.callDao = callDao;
    }

    public RateDao getRateDao() {
        return rateDao;
    }

    public void setRateDao(RateDao rateDao) {
        this.rateDao = rateDao;
    }
    
    @Override
    public Call getById(Long id) throws ServiceException {
        try {
            return callDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Call call) throws ServiceException {
        try {
            if(call.getId() != null) {
                callDao.update(call);
            } else {
                Rate rate = rateDao.readCurrentByType(call.getRateType());
                call.setRate(rate);
                callDao.create(call);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
