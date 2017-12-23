package station.dao.mysql.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.user.AdministratorDao;
import station.dao.mysql.BaseDao;
import station.domain.user.Administrator;
import station.exception.DaoException;

public class AdministratorDaoImpl extends BaseDao implements AdministratorDao {

    public AdministratorDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Administrator administrator) throws DaoException {
        String query = "INSERT INTO `administrators` (`id`, `personalID`, "
                + "`position`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, administrator.getId());
            statement.setInt(2, administrator.getPersonalId());
            statement.setString(3, administrator.getPosition());
            statement.executeUpdate();
            return administrator.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public Administrator read(Long id) throws DaoException {
        String query = "SELECT `personalID`, `position` "
                + " FROM `administrators` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Administrator administrator = null;
            if (resultSet.next()) {
                administrator = new Administrator();
                administrator.setId(id);
                administrator.setPersonalId(resultSet.getInt("personalID"));
                administrator.setPosition(resultSet.getString("position"));
            }
            return administrator;
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
    public void update(Administrator administrator) throws DaoException {
        String query = "UPDATE `administrators` SET `personalID` = ?, "
                + "`position` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, administrator.getPersonalId());
            statement.setString(2, administrator.getPosition());
            statement.setLong(3, administrator.getUser().getId());
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
        String query = "DELETE FROM `administrators` WHERE `id` = ?";
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
