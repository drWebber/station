package station.dao;

import station.domain.Entity;
import station.exception.DaoException;

public interface Dao<PK, T extends Entity<PK>> {
    PK create(T object) throws DaoException;

    T read(PK key) throws DaoException;

    void update(T object) throws DaoException;

    void delete(PK key) throws DaoException;
}
