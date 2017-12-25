package station.dao;

import station.domain.Entity;
import station.exception.DaoException;

public interface CompleteDao<PK, T extends Entity<PK>>
        extends UpdatableDao<PK, T> {
    void delete(PK id) throws DaoException;
}
