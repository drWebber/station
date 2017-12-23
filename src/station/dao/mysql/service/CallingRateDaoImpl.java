package station.dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.service.CallingRateDao;
import station.dao.mysql.BaseDao;
import station.domain.service.CallingRate;
import station.exception.DaoException;

public class CallingRateDaoImpl extends BaseDao implements CallingRateDao {

    public CallingRateDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Short create(CallingRate rate) throws DaoException {
        String query = "INSERT INTO `calling_rates` (`name`, `rate`) "
                + "VALUES(?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, rate.getName());
            statement.setFloat(2, rate.getRate());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getShort(1);
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
    public CallingRate read(Short id) throws DaoException {
        String query = "SELECT `name`, `rate` FROM `calling_rates` "
                + "WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setShort(1, id);
            resultSet = statement.executeQuery();
            CallingRate rate = null;
            if (resultSet.next()) {
                rate = new CallingRate();
                rate.setId(id);
                rate.setName(resultSet.getString("name"));
                rate.setRate(resultSet.getFloat("rate"));
            }
            return rate;
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
    public void update(CallingRate rate) throws DaoException {
        String query = "UPDATE `calling_rates` SET `name` = ?, "
                + "`rate` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, rate.getName());
            statement.setFloat(2, rate.getRate());
            statement.setShort(3, rate.getId());
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
    public void delete(Short id) throws DaoException {
        String query = "DELETE FROM `calling_rates` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setShort(1, id);
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
