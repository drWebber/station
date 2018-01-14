package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.SubscriptionDao;
import dao.mysql.BaseDao;
import domain.service.Offer;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.DaoException;

public class SubscriptionDaoImpl extends BaseDao implements SubscriptionDao {
    public SubscriptionDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Subscription service) throws DaoException {
        String query = "INSERT INTO `subscriptions`(`subscriberID`, `offerID`) "
                + "VALUES(?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, service.getSubscriber().getId());
            statement.setInt(2, service.getOffer().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
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
    public Subscription read(Long id) throws DaoException {
        String query = "SELECT * FROM `subscriptions` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Subscription subscription = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                subscription = getService(resultSet);
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
        return subscription;
    }

    @Override
    public List<Subscription> readSubscriptions(Subscriber subscriber,
            boolean readArchieved) throws DaoException {
        String operator = "IS";
        if (readArchieved) {
            operator += " NOT";
        }
        String query = String.format("SELECT * FROM `subscriptions` WHERE "
                + "`subscriberID` = ? AND `disconnected` %s NULL", operator);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Subscription> services = new ArrayList<>();
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subscription service = getService(resultSet);
                services.add(service);
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
        return services;
    }

    @Override
    public void update(Subscription service) throws DaoException {
        String query = "UPDATE `subscriptions` SET `disconnected` "
                + "= CURRENT_TIMESTAMP WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, service.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    private Subscription getService(ResultSet resultSet) throws SQLException {
        Subscription service = new Subscription();
        service.setId(resultSet.getLong("id"));
        Subscriber subscriber = new Subscriber();
        subscriber.setId(resultSet.getLong("subscriberID"));
        service.setSubscriber(subscriber);
        Offer offer = new Offer();
        offer.setId(resultSet.getInt("offerID"));
        service.setOffer(offer);
        service.setConnected(resultSet.getTimestamp("connected"));
        service.setDisconnected(resultSet.getTimestamp("disconnected"));
        return service;
    }
}
