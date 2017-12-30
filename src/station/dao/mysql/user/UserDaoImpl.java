package station.dao.mysql.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.user.UserDao;
import station.dao.mysql.BaseDao;
import station.domain.user.Role;
import station.domain.user.User;
import station.exception.DaoException;

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
            } catch (NullPointerException | SQLException e) {}
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public User read(Long id) throws DaoException {
        String query = "SELECT `login`, `password`, `surname`, `name`"
                + ", `patronymic`, `role`, `isActive` FROM `users` "
                + "WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setActive(resultSet.getBoolean("isActive"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { 
                resultSet.close(); 
            } catch (NullPointerException | SQLException e) {}
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public void update(User user) throws DaoException {
        //TODO: удалить если не нужно: `login` = ?, `password` = ?, 
        String query = "UPDATE `users` SET `surname` = ?, `name` = ?, "
                + "`patronymic` = ?, `role` = ?, `isActive` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            //TODO: удалить, если не нужно, НЕ ПЕРЕПУТАТЬ НОМЕРАЦИЮ!!!, surname без логина+пароля начинается с ЕДИНИЦЫ:
            //statement.setString(1, user.getLogin());
            //statement.setString(2, user.getPassword());
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getRole().name());
            statement.setBoolean(5, user.isActive());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
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
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }
}
