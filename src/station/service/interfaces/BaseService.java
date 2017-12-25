package station.service.interfaces;

import station.domain.Entity;
import station.exception.ServiceException;

public interface BaseService<PK, T extends Entity<PK>> {
    T getById(PK id) throws ServiceException;
    void save(T entity) throws ServiceException;
}
