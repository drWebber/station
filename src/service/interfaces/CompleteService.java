package service.interfaces;

import domain.Entity;
import exception.service.ServiceException;

public interface CompleteService <PK, T extends Entity<PK>> 
        extends BaseService<PK, T> {
    void delete(PK id) throws ServiceException;
}
