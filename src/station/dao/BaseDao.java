package station.dao;

import station.domain.Entity;
import station.exception.DaoException;

public interface BaseDao<PK, T extends Entity<PK>> {
    PK create(T entity) throws DaoException;
    T read(PK id) throws DaoException;
}
