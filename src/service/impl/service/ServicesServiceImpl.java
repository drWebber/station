package service.impl.service;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.ProvidedServicesDao;
import dao.interfaces.service.ServicesDao;
import domain.service.ProvidedService;
import domain.service.Service;
import domain.user.Subscriber;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.service.ServicesService;

public class ServicesServiceImpl implements ServicesService {
    private ServicesDao servicesDao;
    private ProvidedServicesDao providedSrvDao;

    public ServicesDao getServicesDao() {
        return servicesDao;
    }

    public void setServicesDao(ServicesDao servicesDao) {
        this.servicesDao = servicesDao;
    }

    public ProvidedServicesDao getProvidedSrvDao() {
        return providedSrvDao;
    }

    public void setProvidedSrvDao(ProvidedServicesDao providedSrvDao) {
        this.providedSrvDao = providedSrvDao;
    }
    
    @Override
    public Service getById(Long id) throws ServiceException {
        try {
            return servicesDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Service> getSubscriberServices(Subscriber subscriber)
            throws ServiceException {
        try {
            List<Service> services = new ArrayList<>();
            services = servicesDao.readSubscriberServices(subscriber);
            for (Service service : services) {
                ProvidedService providedSrv = 
                        providedSrvDao.read(service.getProvidedService().getId());
                service.setProvidedService(providedSrv);
            }
            return services;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Service service) throws ServiceException {
        try {
            if(service.getId() != null) {
                servicesDao.update(service);
            } else {
                servicesDao.create(service);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
