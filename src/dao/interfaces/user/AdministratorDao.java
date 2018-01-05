package dao.interfaces.user;

import java.util.List;

import dao.CompleteDao;
import domain.user.Administrator;
import exception.DaoException;

public interface AdministratorDao extends CompleteDao<Long, Administrator> {
    List<Administrator> readAll() throws DaoException;
}
