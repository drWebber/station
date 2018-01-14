package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaces.service.SubscriptionsDetailDao;
import dao.mysql.BaseDao;
import domain.service.SubscriptionsDetail;
import domain.user.Subscriber;
import exception.DaoException;

public class SubscriptionsDetailDaoImpl extends BaseDao
    implements SubscriptionsDetailDao {

    public SubscriptionsDetailDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public SubscriptionsDetail getBySubscriber(Subscriber subscriber)
            throws DaoException {
        String query = ""
                + "SELECT m.`subscriberID`, m.`sum` AS `majorFee`, t.`fee`, "
                    + "t.`subscriptionCost` "
                + "FROM vw_mon_major_fee AS m "
                + "LEFT JOIN "
                    + "(SELECT c.`subscriberID`, c.`sum` AS `subscriptionCost`,"
                        + " f.`sum` AS `fee` "
                    + "FROM vw_mon_subscriptions_cost AS c "
                    + "LEFT JOIN vw_mon_srv_fee AS f "
                        + "ON c.`subscriberID` = f.`subscriberID`) AS t "
                + "ON m.`subscriberID` = t.`subscriberID` "
                + "WHERE m.`subscriberID` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SubscriptionsDetail detail = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                detail = new SubscriptionsDetail();
                detail.setSubscriber(subscriber);
                detail.setMajorFee(resultSet.getBigDecimal("majorFee"));
                detail.setFee(resultSet.getBigDecimal("fee"));
                detail.setSubscriptionsCost(
                        resultSet.getBigDecimal("subscriptionCost"));
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
        return detail;
    }
}
