package dao;

import domain.Entity;
import exception.DaoException;

public interface BaseDao<PK, T extends Entity<PK>> {
    PK create(final T entity) throws DaoException;
    T read(final PK id) throws DaoException;
}
