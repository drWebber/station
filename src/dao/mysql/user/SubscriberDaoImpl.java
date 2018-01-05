package dao.mysql.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.user.SubscriberDao;
import dao.mysql.BaseDao;
import domain.user.Administrator;
import domain.user.Prefix;
import domain.user.Subscriber;
import exception.DaoException;

public class SubscriberDaoImpl extends BaseDao implements SubscriberDao {

    public SubscriberDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Subscriber subscriber) throws DaoException {
        String query = "INSERT INTO `subscribers` (`id`, `passportID`, `dob`, "
                + "`address`, `prefixID`, `phoneNum`, `administratorID`) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            statement.setString(2, subscriber.getPassportId());
            statement.setDate(3, subscriber.getBirthDay());
            statement.setString(4, subscriber.getAddress());
            statement.setInt(5, subscriber.getPrefix().getId());
            statement.setInt(6, subscriber.getPhoneNum());
            statement.setLong(7, subscriber.getAdministrator().getId());
            statement.executeUpdate();
            return subscriber.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public Subscriber read(Long id) throws DaoException {
        String query = "SELECT * FROM `subscribers` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Subscriber subscriber = null;
            if (resultSet.next()) {
                subscriber = getSubscriber(resultSet);
            }
            return subscriber;
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
    public List<Subscriber> readAll() throws DaoException {
        String query = "SELECT * FROM `subscribers`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            List<Subscriber> subscribers = new ArrayList<>();
            while (resultSet.next()){
                subscribers.add(getSubscriber(resultSet));
            }
            return subscribers;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) { }
            try { statement.close(); } catch (SQLException e) { }
        }
    }

    @Override
    public void update(Subscriber subscriber) throws DaoException {
        String query = "UPDATE `subscribers` SET `passportID` = ?, `dob` = ?, "
                + "`address` = ?, `prefixID` = ?, `phoneNum` = ?, "
                + "`administratorID` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, subscriber.getPassportId());
            statement.setDate(2, subscriber.getBirthDay());
            statement.setString(3, subscriber.getAddress());
            statement.setInt(4, subscriber.getPrefix().getId());
            statement.setInt(5, subscriber.getPhoneNum());
            statement.setLong(6, subscriber.getAdministrator().getId());
            statement.setLong(7, subscriber.getId());
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
    public void delete(Long id) throws DaoException {
        String query = "DELETE FROM `subscribers` WHERE `id` = ?";
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

    private Subscriber getSubscriber(ResultSet resultSet) throws SQLException {
        Subscriber subscriber = new Subscriber();
        subscriber.setId(resultSet.getLong("id"));
        subscriber.setPassportId(resultSet.getString("passportID"));
        subscriber.setBirthDay(resultSet.getDate("dob"));
        subscriber.setAddress(resultSet.getString("address"));
        Prefix prefix = new Prefix();
        prefix.setId(resultSet.getInt("prefixID"));
        subscriber.setPrefix(prefix);
        subscriber.setPhoneNum(resultSet.getInt("phoneNum"));
        Administrator administrator = new Administrator();
        administrator.setId(resultSet.getLong("administratorID"));
        subscriber.setAdministrator(administrator);
        return subscriber;
    }
}
