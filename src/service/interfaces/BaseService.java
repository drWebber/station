package service.interfaces;

import domain.Entity;
import exception.service.ServiceException;

public interface BaseService<PK, T extends Entity<PK>> {
    T getById(PK id) throws ServiceException;
    void save(T entity) throws ServiceException;
}
