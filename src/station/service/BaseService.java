package station.service;

import station.domain.Entity;
import station.exception.ServiceException;

public interface BaseService<PK, T extends Entity> {
    T getById(PK id) throws ServiceException;
    
    void save(T entity) throws ServiceException;
    
    void delete(PK id) throws ServiceException;
}
