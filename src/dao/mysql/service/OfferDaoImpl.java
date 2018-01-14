package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.OfferDao;
import dao.mysql.BaseDao;
import domain.service.Offer;
import exception.DaoException;

public class OfferDaoImpl extends BaseDao implements
        OfferDao {

    public OfferDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(Offer providedService) throws DaoException {
        String query = "INSERT INTO `offers` (`name`, "
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
            } catch (NullPointerException | SQLException e) { }
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
    }

    @Override
    public Offer read(Integer id) throws DaoException {
        String query = "SELECT * FROM `offers` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Offer offer = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                offer = getService(resultSet);
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
        return offer;
    }

    @Override
    public List<Offer> readAll() throws DaoException {
        String query = "SELECT * FROM `offers`";
        Statement statement = null;
        ResultSet resultSet = null;
        List<Offer> offers = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Offer offer = getService(resultSet);
                offers.add(offer);
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
        return offers;
    }

    @Override
    public List<Offer> readByRequirement(boolean require)
            throws DaoException {
        String query = "SELECT * FROM `offers` WHERE `required` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Offer> offers = new ArrayList<>();
        try {
            statement = getConnection().prepareStatement(query);
            statement.setBoolean(1, require);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Offer offer = getService(resultSet);
                offers.add(offer);
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
        return offers;
    }

    @Override
    public void update(Offer providedService) throws DaoException {
        String query = "UPDATE `offers` SET `name` = ?, "
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
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String query = "DELETE FROM `offers` WHERE `id` = ?";
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

    private Offer getService(ResultSet resultSet)
            throws SQLException {
        Offer service = new Offer();
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
