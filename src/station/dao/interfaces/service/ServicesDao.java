package station.dao.interfaces.service;

import java.util.List;

import station.dao.UpdatableDao;
import station.domain.service.Service;
import station.domain.user.Subscriber;
import station.exception.DaoException;

public interface ServicesDao extends UpdatableDao<Long, Service> {
    List<Service> readSubscriberServices(Subscriber subscriber)
        throws DaoException;
}
