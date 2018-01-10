package dao.mysql.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.user.AdministratorDao;
import dao.mysql.BaseDao;
import domain.user.Administrator;
import exception.DaoException;

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
        String query = "SELECT * FROM `administrators` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Administrator administrator = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                administrator = getAdministrator(resultSet);
            }
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
        return administrator;
    }

    @Override
    public List<Administrator> readAll() throws DaoException {
        String query = "SELECT * FROM `administrators`";
        Statement statement = null;
        ResultSet resultSet = null;
        List<Administrator> administrators = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                administrators.add(getAdministrator(resultSet));
            }
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
        return administrators;
    }
    
    private Administrator getAdministrator(ResultSet resultSet) 
            throws SQLException {
        Administrator administrator = new Administrator();
        administrator.setId(resultSet.getLong("id"));
        administrator.setPersonalId(resultSet.getInt("personalID"));
        administrator.setPosition(resultSet.getString("position"));
        return administrator;
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
            statement.setLong(3, administrator.getId());
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
