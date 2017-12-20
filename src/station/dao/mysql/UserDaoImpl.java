package station.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.user.UserDao;
import station.domain.user.Role;
import station.domain.user.User;
import station.exception.DaoException;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public Long create(User user) throws DaoException {
        String query = "INSERT INTO `users` (`login`, `password`, `fullName`, "
                + "`role`, `isActive`) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getRole().name());
            statement.setBoolean(5, user.getActivityState());
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
        String query = "SELECT `login`, `password`, `fullName`, `role`"
                + ", `isActive` FROM `users` WHERE `id` = ?";
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
                user.setFullName(resultSet.getString("fullName"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setActivityState(resultSet.getBoolean("isActive"));
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
        String query = "UPDATE `users` SET `login` = ?, `password` = ?, "
                + "`fullName` = ?, `role` = ?, `isActive` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, String.valueOf(user.getRole().ordinal()));
            statement.setBoolean(5, user.getActivityState());
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
