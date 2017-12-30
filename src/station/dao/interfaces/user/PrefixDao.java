package station.dao.interfaces.user;

import java.util.List;

import station.dao.CompleteDao;
import station.domain.user.Prefix;
import station.exception.DaoException;

public interface PrefixDao extends CompleteDao<Integer, Prefix> {
    List<Prefix> readAll() throws DaoException;
}
