package dao;

import domain.Entity;
import exception.DaoException;

public interface BaseDao<PK, T extends Entity<PK>> {
    PK create(T entity) throws DaoException;
    T read(PK id) throws DaoException;
}
