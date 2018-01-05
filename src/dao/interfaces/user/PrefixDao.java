package dao.interfaces.user;

import java.util.List;

import dao.CompleteDao;
import domain.user.Prefix;
import exception.DaoException;

public interface PrefixDao extends CompleteDao<Integer, Prefix> {
    List<Prefix> readAll() throws DaoException;
}
