package service.interfaces.service;

import java.util.List;

import domain.service.Service;
import domain.user.Subscriber;
import exception.ServiceException;
import service.interfaces.BaseService;

public interface ServicesService extends BaseService<Long, Service> {
    List<Service> getSubscriberServices(Subscriber subscriber)
        throws ServiceException;
}
