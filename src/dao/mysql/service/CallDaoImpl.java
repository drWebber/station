package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaces.service.CallDao;
import dao.mysql.BaseDao;
import domain.service.Call;
import domain.service.Rate;
import domain.user.Prefix;
import domain.user.Subscriber;
import exception.DaoException;

public class CallDaoImpl extends BaseDao implements CallDao {

    public CallDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Call call) throws DaoException {
        String query = "INSERT INTO `calls` (`subscriberID`, `prefixID`, "
                + "`phoneNum`, `begin`, `finish`, `rateID`) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, call.getSubscriber().getId());
            statement.setInt(2, call.getPrefix().getId());
            statement.setInt(3, call.getPhoneNum());
            statement.setTimestamp(4, call.getBeginTime());
            statement.setTimestamp(5, call.getFinishTime());
            statement.setLong(6, call.getRate().getId());
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
    public Call read(Long id) throws DaoException {
        String query = "SELECT * FROM `calls` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Call call = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                call = getCall(resultSet);
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
        return call;
    }

    private Call getCall(ResultSet resultSet) throws SQLException {
        Call call = new Call();
        call.setId(resultSet.getLong("id"));
        Subscriber subscriber = new Subscriber();
        subscriber.setId(resultSet.getLong("subscriberID"));
        call.setSubscriber(subscriber);
        Prefix prefix = new Prefix();
        prefix.setId(resultSet.getInt("prefixID"));
        call.setPrefix(prefix);
        call.setPhoneNum(resultSet.getInt("phoneNum"));
        call.setBeginTime(resultSet.getTimestamp("begin"));
        call.setFinishTime(resultSet.getTimestamp("finish"));
        Rate rate = new Rate();
        rate.setId(resultSet.getLong("rateID"));
        call.setRate(rate);
        return call;
    }

    @Override
    public void update(Call call) throws DaoException {
        String query = "UPDATE `calls` SET `finish` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setTimestamp(1, call.getFinishTime());
            statement.setLong(2, call.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
    }
}
