package station.service.impl.service;

import java.util.List;

import station.dao.interfaces.service.ProvidedServicesDao;
import station.domain.service.ProvidedService;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.service.ProvidedServicesService;

public class ProvidedServicesServiceImpl implements ProvidedServicesService {
    private ProvidedServicesDao providedSrvDao;
    
    public ProvidedServicesDao getProvidedSrvDao() {
        return providedSrvDao;
    }

    public void setProvidedSrvDao(ProvidedServicesDao providedSrvDao) {
        this.providedSrvDao = providedSrvDao;
    }

    @Override
    public ProvidedService getById(Integer id) throws ServiceException {
        try {
            return providedSrvDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ProvidedService> getAll() throws ServiceException {
        try {
            return providedSrvDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(ProvidedService service) throws ServiceException {
        try {
            if(service.getId() != null) {
                providedSrvDao.update(service);
            } else {
                providedSrvDao.create(service);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            providedSrvDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
