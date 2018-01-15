package dao;

import domain.Entity;
import exception.DaoException;

public interface CompleteDao<PK, T extends Entity<PK>>
        extends UpdatableDao<PK, T> {
    void delete(final PK id) throws DaoException;
}
