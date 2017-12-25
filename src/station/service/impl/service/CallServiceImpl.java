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
                callDao.create(call);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
