package station.service.impl.service;

import station.dao.interfaces.service.CallDao;
import station.domain.service.Call;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.service.CallService;

public class CallServiceImpl implements CallService {
    private CallDao callDao;

    public CallDao getCallDao() {
        return callDao;
    }

    public void setCallDao(CallDao callDao) {
        this.callDao = callDao;
    }
    
    @Override
    public Call getById(Long id) throws ServiceException {
        try {
            Call call = callDao.read(id);
            return call;
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
                callDao.create(call);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        throw new ServiceException("Restricted operation");
        //TODO Удалить код ниже (если не нужен):
//        try {
//            callDao.delete(id);
//        } catch(DaoException e) {
//            throw new ServiceException(e);
//        }
    }
}
