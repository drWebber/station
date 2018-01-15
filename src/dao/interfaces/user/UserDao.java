package dao.interfaces.user;

import dao.CompleteDao;
import domain.user.User;
import exception.DaoException;

public interface UserDao extends CompleteDao<Long, User> {
    User readByLoginAndPassword(String login, String password)
            throws DaoException;

    void banById(Long id) throws DaoException;

    boolean isBanned(Long id) throws DaoException;
    
    boolean isLoginUnique(String login) throws DaoException;
}
