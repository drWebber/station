package station.dao;

import station.domain.Entity;
import station.exception.DaoException;

public interface UpdatableDao<PK, T extends Entity<PK>> extends BaseDao<PK, T> {
    void update(T entity) throws DaoException;
}
