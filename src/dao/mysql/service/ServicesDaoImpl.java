package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.ServicesDao;
import dao.mysql.BaseDao;
import domain.service.ProvidedService;
import domain.service.Service;
import domain.user.Subscriber;
import exception.DaoException;

public class ServicesDaoImpl extends BaseDao implements ServicesDao {

    public ServicesDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Service service) throws DaoException {
        String query = "INSERT INTO `services`(`subscriberID`, `serviceID`) "
                + "VALUES(?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, service.getSubscriber().getId());
            statement.setInt(2, service.getProvidedService().getId());
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
        String query = "SELECT * FROM `services` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Service service = null;
            if (resultSet.next()) {
                service = getService(resultSet);
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
    public List<Service> readSubscriberServices(Subscriber subscriber)
            throws DaoException {
        String query = "SELECT * FROM `services` WHERE `subscriberID` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            resultSet = statement.executeQuery();
            List<Service> services = new ArrayList<>();
            while (resultSet.next()) {
                Service service = getService(resultSet);
                services.add(service);
            }
            return services;
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
        String query = "UPDATE `services` SET `disconnected` "
                + "= CURRENT_TIMESTAMP WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, service.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try { 
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }  
    }

    private Service getService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getLong("id"));
        Subscriber subscriber = new Subscriber();
        subscriber.setId(resultSet.getLong("subscriberID"));
        service.setSubscriber(subscriber);
        ProvidedService providedService = new ProvidedService();
        providedService.setId(resultSet.getInt("serviceID"));
        service.setProvidedService(providedService);
        service.setConnected(resultSet.getTimestamp("connected"));
        service.setDisconnected(resultSet.getTimestamp("disconnected"));
        return service;
    }
}
