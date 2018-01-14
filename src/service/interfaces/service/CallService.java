package service.interfaces.service;

import domain.service.Call;
import exception.ServiceException;
import exception.UserIsBannedException;
import service.interfaces.BaseService;

public interface CallService extends BaseService<Long, Call> {

    void validateAndsave(Call call) throws ServiceException,
            UserIsBannedException;
}
