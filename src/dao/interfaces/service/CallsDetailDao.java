package dao.interfaces.service;

import java.util.List;

import domain.service.CallsDetail;
import domain.user.Subscriber;
import exception.DaoException;

public interface CallsDetailDao {
    List<CallsDetail> getBySubscriber(Subscriber subscriber)
            throws DaoException;
}
