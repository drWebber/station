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
import domain.user.Subscriber;
import exception.DaoException;

public class OfferDaoImpl extends BaseDao implements
        OfferDao {

    public OfferDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(final Offer offer) throws DaoException {
        String query = "INSERT INTO `offers` (`name`, "
                + "`description`, `monthlyFee`, `subscriptionRate`, "
                + "`required`) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, offer.getName());
            statement.setString(2, offer.getDescription());
            statement.setFloat(3, offer.getMonthlyFee());
            statement.setFloat(4, offer.getSubscriptionRate());
            statement.setBoolean(5, offer.isRequired());
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
    public Offer read(final Integer id) throws DaoException {
        String query = "SELECT * FROM `offers` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Offer offer = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                offer = getOffer(resultSet);
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
                Offer offer = getOffer(resultSet);
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
    public List<Offer> readByRequirement(final boolean require)
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
                Offer offer = getOffer(resultSet);
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
    public void update(final Offer offer) throws DaoException {
        String query = "UPDATE `offers` SET `name` = ?, "
                + "`description` = ?, `monthlyFee` = ?, "
                + "`subscriptionRate` = ?, `required` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, offer.getName());
            statement.setString(2, offer.getDescription());
            statement.setFloat(3, offer.getMonthlyFee());
            statement.setFloat(4, offer.getSubscriptionRate());
            statement.setBoolean(5, offer.isRequired());
            statement.setInt(6, offer.getId());
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
    public void delete(final Integer id) throws DaoException {
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

    private Offer getOffer(final ResultSet resultSet)
            throws SQLException {
        Offer offer = new Offer();
        offer.setId(resultSet.getInt("id"));
        offer.setName(resultSet.getString("name"));
        offer.setDescription(resultSet.getString("description"));
        offer.setMonthlyFee(resultSet.getFloat("monthlyFee"));
        offer.setSubscriptionRate(resultSet
                .getFloat("subscriptionRate"));
        offer.setRequired(resultSet.getBoolean("required"));
        return offer;
    }

    @Override
    public List<Offer> readSubscribedOffers(final Subscriber subscriber)
            throws DaoException {
        String query = "SELECT * "
                     + "FROM `offers` "
                         + "WHERE `required` = 0 "
                             + "AND `id` IN("
                                 + "SELECT `offerID` "
                                 + "FROM `subscriptions` "
                                 + "WHERE `subscriberID` = ? "
                                     + "AND `disconnected` IS NULL)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            resultSet = statement.executeQuery();
            List<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                offers.add(getOffer(resultSet));
            }
            return offers;
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
}
