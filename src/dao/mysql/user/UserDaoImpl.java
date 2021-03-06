package dao.mysql.user;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.user.PasswordCryptographer;
import dao.interfaces.user.UserDao;
import dao.mysql.BaseDao;
import domain.user.Role;
import domain.user.User;
import exception.DaoException;

public class UserDaoImpl extends BaseDao implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(User user) throws DaoException {
        String query = "INSERT INTO `users` (`login`, `password`, `surname`, "
                + "`name`, `patronymic`, `role`, `isActive`) VALUES(?, ?, ?, "
                + "?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getName());
            statement.setString(5, user.getPatronymic());
            statement.setString(6, user.getRole().name());
            statement.setBoolean(7, user.isActive());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { 
                resultSet.close();
            } catch (NullPointerException | SQLException e) { }
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
    }

    @Override
    public User read(Long id) throws DaoException {
        String query = "SELECT * FROM `users` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (NullPointerException | SQLException e) { }
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
        return user;
    }

    @Override
    public User readByLoginAndPassword(String login, String password)
            throws DaoException {
        String query = "SELECT * FROM `users` WHERE login = ? AND password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = getConnection().prepareStatement(query);
            password = new PasswordCryptographer(login, password)
                .getCryptedPassword();
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (NullPointerException | SQLException e) { }
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
        return user;
    }

    private User getUser(final ResultSet resultSet)
            throws SQLException, DaoException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setSurname(resultSet.getString("surname"));
        user.setName(resultSet.getString("name"));
        user.setPatronymic(resultSet.getString("patronymic"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setActive(resultSet.getBoolean("isActive"));
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        String query = "UPDATE `users` SET `surname` = ?, `name` = ?, "
                + "`patronymic` = ?, `role` = ?, `isActive` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getRole().name());
            statement.setBoolean(5, user.isActive());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
    }

    @Override
    public void banById(Long id) throws DaoException {
        String query = "UPDATE `users` SET `isActive` = 0 WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String query = "DELETE FROM `users` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public boolean isBanned(final Long id) throws DaoException {
        String query = "SELECT `isActive` FROM `users` WHERE `id`=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isActive = true;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isActive = resultSet.getBoolean("isActive");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (NullPointerException | SQLException e) { }
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
        return !isActive;
    }

    @Override
    public boolean isLoginUnique(String login) throws DaoException {
        String query = "SELECT `id` FROM `users` WHERE `login` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return resultSet.next() ? false : true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (NullPointerException | SQLException e) { }
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
    }
}
