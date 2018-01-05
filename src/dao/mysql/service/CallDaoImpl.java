package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaces.service.CallDao;
import dao.mysql.BaseDao;
import domain.service.Call;
import domain.service.CallingRate;
import domain.user.Subscriber;
import exception.DaoException;

public class CallDaoImpl extends BaseDao implements CallDao {

    public CallDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Call call) throws DaoException {
        String query = "INSERT INTO `calls` (`subscriberID`, `phoneNum`, "
                + "`begin`, `finish`, `rateID`) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, call.getSubscriber().getId());
            statement.setInt(2, call.getPhoneNum());
            statement.setTimestamp(3, call.getBeginTime());
            statement.setTimestamp(4, call.getFinishTime());
            statement.setShort(5, call.getRate().getId());
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
    public Call read(Long id) throws DaoException {
        String query = "SELECT `subscriberID`, `phoneNum`, `begin`, `finish`, "
                + "`rateID` FROM `calls` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Call call = null;
            if (resultSet.next()) {
                call = new Call();
                call.setId(id);
                Subscriber subscriber = new Subscriber();
                subscriber.setId(resultSet.getLong("subscriberID"));
                call.setSubscriber(subscriber);
                call.setPhoneNum(resultSet.getInt("phoneNum"));
                call.setBeginTime(resultSet.getTimestamp("begin"));
                call.setFinishTime(resultSet.getTimestamp("finish"));
                CallingRate rate = new CallingRate();
                rate.setId(resultSet.getShort("rateID"));
                call.setRate(rate);
            }
            return call;
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
    public void update(Call call) throws DaoException {
        String query = "UPDATE `calls` SET `subscriberID` = ?, "
                + "`phoneNum` = ?, `begin` = ?, `finish` = ?, `rateID` = ? "
                + "WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, call.getSubscriber().getId());
            statement.setInt(2, call.getPhoneNum());
            statement.setTimestamp(3, call.getBeginTime());
            statement.setTimestamp(4, call.getFinishTime());
            statement.setShort(5, call.getRate().getId());
            statement.setLong(6, call.getId());
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
