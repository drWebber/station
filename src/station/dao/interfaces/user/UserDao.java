package station.dao.interfaces.user;

import station.dao.CompleteDao;
import station.domain.user.User;
import station.exception.DaoException;

public interface UserDao extends CompleteDao<Long, User> {
    User readByLoginAndPassword(String login, String password) 
            throws DaoException;
}
