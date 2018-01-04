package station.service.interfaces.service;

import java.util.List;

import station.domain.service.Service;
import station.domain.user.Subscriber;
import station.exception.ServiceException;
import station.service.interfaces.BaseService;

public interface ServicesService extends BaseService<Long, Service> {
    List<Service> getSubscriberServices(Subscriber subscriber)
        throws ServiceException;
}
