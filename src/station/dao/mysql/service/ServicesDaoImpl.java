package station.dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.service.ServicesDao;
import station.dao.mysql.BaseDao;
import station.domain.service.ProvidedService;
import station.domain.service.Service;
import station.domain.user.Subscriber;
import station.exception.DaoException;

public class ServicesDaoImpl extends BaseDao implements ServicesDao {

    public ServicesDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Service service) throws DaoException {
        String query = "INSERT INTO `services`(`subscriberID`, `serviceID`, "
                + "`connected`, `disconnected`) VALUES(?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, service.getSubscriber().getId());
            statement.setInt(2, service.getService().getId());
            statement.setTimestamp(3, service.getConnected());
            statement.setTimestamp(4, service.getDisconnected());
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
    public Service read(Long id) throws DaoException {
        String query = "SELECT `subscriberID`, `serviceID`, `connected`, "
                + "`disconnected` FROM `services` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Service service = null;
            if (resultSet.next()) {
                service = new Service();
                Subscriber subscriber = new Subscriber();
                subscriber.setId(resultSet.getLong("subscriberID"));
                service.setSubscriber(subscriber);
                ProvidedService providedService = new ProvidedService();
                providedService.setId(resultSet.getInt("serviceID"));
                service.setService(providedService);
                service.setConnected(resultSet.getTimestamp("connected"));
                service.setDisconnected(resultSet.getTimestamp("disconnected"));
            }
            return service;
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
    public void update(Service service) throws DaoException {
        String query = "UPDATE `services` SET `subscriberID` = ?, "
                + "`serviceID` = ?, `connected` = ?, `disconnected` = ? "
                + "WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, service.getSubscriber().getId());
            statement.setInt(2, service.getService().getId());
            statement.setTimestamp(3, service.getConnected());
            statement.setTimestamp(4, service.getDisconnected());
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
        String query = "DELETE FROM `services` WHERE `id` = ?";
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
