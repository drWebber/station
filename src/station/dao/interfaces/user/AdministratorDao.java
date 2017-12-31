package station.dao.interfaces.user;

import java.util.List;

import station.dao.CompleteDao;
import station.domain.user.Administrator;
import station.exception.DaoException;

public interface AdministratorDao extends CompleteDao<Long, Administrator> {
    List<Administrator> readAll() throws DaoException;
}
