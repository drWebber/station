package station.dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.service.ProvidedServicesDao;
import station.dao.mysql.BaseDao;
import station.domain.service.ProvidedService;
import station.exception.DaoException;

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
        String query = "SELECT `name`, `description`, `monthlyFee`, "
                + "`subscriptionRate`, `required` FROM `provided_services` "
                + "WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ProvidedService service = null;
            if (resultSet.next()) {
                service = new ProvidedService();
                service.setId(id);
                service.setName(resultSet.getString("name"));
                service.setDescription(resultSet.getString("description"));
                service.setMonthlyFee(resultSet.getFloat("monthlyFee"));
                service.setSubscriptionRate(resultSet
                        .getFloat("subscriptionRate"));
                service.setRequired(resultSet.getBoolean("required"));
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
}
