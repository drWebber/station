package service.interfaces.service;

import java.util.List;

import service.interfaces.BaseService;
import domain.service.Service;
import domain.user.Subscriber;
import exception.ServiceException;

public interface ServicesService extends BaseService<Long, Service> {
    List<Service> getSubscriberServices(Subscriber subscriber)
        throws ServiceException;
}
