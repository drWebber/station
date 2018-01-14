package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.CallsDetailDao;
import dao.mysql.BaseDao;
import domain.service.CallsDetail;
import domain.service.RateType;
import domain.user.Subscriber;
import exception.DaoException;

public class CallsDetailDaoImpl extends BaseDao implements CallsDetailDao {

    public CallsDetailDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<CallsDetail> getBySubscriber(Subscriber subscriber)
            throws DaoException {
        String query = ""
                + "SELECT * "
                + "FROM station.vw_mon_calls_detailed "
                + "WHERE `subscriberID` = ? "
                + "ORDER BY `rateType`";
        List<CallsDetail> callsDetail = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CallsDetail details = new CallsDetail();
                details.setSubscriber(subscriber);
                details.setRateType(
                        RateType.valueOf(resultSet.getString("rateType")));
                details.setDuration(resultSet.getInt("duration"));
                details.setTariff(resultSet.getFloat("tariff"));
                callsDetail.add(details);
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
        return callsDetail;
    }
}
