package service.impl.service;

import service.impl.TransactionService;
import service.interfaces.service.CallService;
import dao.interfaces.service.CallDao;
import dao.interfaces.service.RateDao;
import dao.interfaces.user.UserDao;
import domain.service.Call;
import domain.service.Rate;
import exception.DaoException;
import exception.ServiceException;
import exception.TransactionException;
import exception.UserIsBannedException;

public class CallServiceImpl extends TransactionService
        implements CallService {
    private CallDao callDao;
    private RateDao rateDao;
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Call call) throws ServiceException {
        try {
            if (call.getId() != null) {
                callDao.update(call);
            } else {
                Rate rate = rateDao.readCurrentByType(call.getRateType());
                call.setRate(rate);
                callDao.create(call);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void validateAndsave(Call call)
            throws ServiceException, UserIsBannedException {
        try {
            getTransaction().start();
            if (call.getId() != null) {
                callDao.update(call);
                getTransaction().commit();
            } else {
                Rate rate = rateDao.readCurrentByType(call.getRateType());
                call.setRate(rate);
                boolean isBanned =
                        userDao.isBanned(
                                call.getSubscriber().getUser().getId());
                callDao.create(call);
                getTransaction().commit();
                if (isBanned) {
                    throw new UserIsBannedException("You are Banned."
                            + "Calling is restricted");
                }
            }
        } catch (DaoException | TransactionException e) {
                try {
                    getTransaction().rollback();
                } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }
}
