package dao.interfaces.service;

import java.util.List;

import dao.UpdatableDao;
import domain.service.Service;
import domain.user.Subscriber;
import exception.DaoException;

public interface ServicesDao extends UpdatableDao<Long, Service> {
    List<Service> readSubscriberServices(Subscriber subscriber)
        throws DaoException;
}
