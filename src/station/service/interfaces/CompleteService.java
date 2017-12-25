package station.service.interfaces;

import station.domain.Entity;
import station.exception.ServiceException;

public interface CompleteService <PK, T extends Entity<PK>> 
        extends BaseService<PK, T> {
    void delete(PK id) throws ServiceException;
}
