package station.service.impl.service;

import station.dao.interfaces.service.ServicesDao;
import station.domain.service.Service;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.service.ServicesService;

public class ServicesServiceImpl implements ServicesService {
    private ServicesDao servicesDao;

    public ServicesDao getServicesDao() {
        return servicesDao;
    }

    public void setServicesDao(ServicesDao servicesDao) {
        this.servicesDao = servicesDao;
    }
    
    @Override
    public Service getById(Long id) throws ServiceException {
        try {
            Service service = servicesDao.read(id);
            return service;
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

    @Override
    public void delete(Long id) throws ServiceException {
        throw new ServiceException("Restricted operation");
        //TODO Удалить код ниже (если не нужен):
//        try {
//            servicesDao.delete(id);
//        } catch(DaoException e) {
//            throw new ServiceException(e);
//        }
    }
}
