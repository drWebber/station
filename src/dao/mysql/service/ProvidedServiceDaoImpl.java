package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.ProvidedServicesDao;
import dao.mysql.BaseDao;
import domain.service.ProvidedService;
import exception.DaoException;

public class ProvidedServiceDaoImpl extends BaseDao implements
        ProvidedServicesDao {

    public ProvidedServiceDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(ProvidedService providedService) throws DaoException {
        String query = "INSERT INTO `provided_services` (`name`, "
                + "`description`, `monthlyFee`, `subscriptionRate`, "
                + "`required`) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, providedService.getName());
            statement.setString(2, providedService.getDescription());
            statement.setFloat(3, providedService.getMonthlyFee());
            statement.setFloat(4, providedService.getSubscriptionRate());
            statement.setBoolean(5, providedService.isRequired());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
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
    public ProvidedService read(Integer id) throws DaoException {
        String query = "SELECT * FROM `provided_services` "
                + "WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ProvidedService service = null;
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
    public List<ProvidedService> readAll() throws DaoException {
        String query = "SELECT * FROM `provided_services`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            List<ProvidedService> services = new ArrayList<>();
            while (resultSet.next()) {
                ProvidedService service = getService(resultSet);
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
    public List<ProvidedService> readByRequirement(boolean require)
            throws DaoException {
        String query = "SELECT * FROM `provided_services` WHERE `required` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setBoolean(1, require);
            resultSet = statement.executeQuery();
            List<ProvidedService> services = new ArrayList<>();
            while (resultSet.next()) {
                ProvidedService service = getService(resultSet);
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
    public void update(ProvidedService providedService) throws DaoException {
        String query = "UPDATE `provided_services` SET `name` = ?, "
                + "`description` = ?, `monthlyFee` = ?, "
                + "`subscriptionRate` = ?, `required` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, providedService.getName());
            statement.setString(2, providedService.getDescription());
            statement.setFloat(3, providedService.getMonthlyFee());
            statement.setFloat(4, providedService.getSubscriptionRate());
            statement.setBoolean(5, providedService.isRequired());
            statement.setInt(6, providedService.getId());
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
    public void delete(Integer id) throws DaoException {
        String query = "DELETE FROM `provided_services` WHERE `id` = ?";
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
    
    private ProvidedService getService(ResultSet resultSet) 
            throws SQLException {
        ProvidedService service = new ProvidedService();
        service.setId(resultSet.getInt("id"));
        service.setName(resultSet.getString("name"));
        service.setDescription(resultSet.getString("description"));
        service.setMonthlyFee(resultSet.getFloat("monthlyFee"));
        service.setSubscriptionRate(resultSet
                .getFloat("subscriptionRate"));
        service.setRequired(resultSet.getBoolean("required"));
        return service;
    }
}
