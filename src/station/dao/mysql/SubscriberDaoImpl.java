package station.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.user.SubscriberDao;
import station.domain.user.Administrator;
import station.domain.user.Subscriber;
import station.exception.DaoException;

public class SubscriberDaoImpl extends BaseDao implements SubscriberDao {
    @Override
    public Long create(Subscriber subscriber) throws DaoException {
        String query = "INSERT INTO `subscribers` (`id`, `address`, `phoneNum`, "
                + "`DOB`, `administratorID`) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            statement.setString(2, subscriber.getAddress());
            statement.setLong(3, subscriber.getPhoneNum());
            statement.setDate(4, subscriber.getBirthDay());
            statement.setLong(5, subscriber.getAdministrator().getId());
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
        String query = "SELECT `address`, `phoneNum`, `DOB`, `administratorID`"
                + " FROM `subscribers` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Subscriber subscriber = null;
            if (resultSet.next()) {
                subscriber = new Subscriber();
                subscriber.setId(id);
                subscriber.setAddress(resultSet.getString("address"));
                subscriber.setPhoneNum(resultSet.getLong("phoneNum"));
                subscriber.setBirthDay(resultSet.getDate("DOB"));
                Administrator administrator = new Administrator();
                administrator.setId(resultSet.getLong("administratorID"));
                subscriber.setAdministrator(administrator);
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
    public void update(Subscriber subscriber) throws DaoException {
        String query = "UPDATE `subscribers` SET `address` = ?, `phoneNum` = ?"
                + ", `DOB` = ?, `administratorID` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, subscriber.getAddress());
            statement.setLong(2, subscriber.getPhoneNum());
            statement.setDate(3, subscriber.getBirthDay());
            statement.setLong(4, subscriber.getAdministrator().getId());
            statement.setLong(5, subscriber.getId());
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
}
