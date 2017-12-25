package station.dao.mysql.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.user.PrefixDao;
import station.dao.mysql.BaseDao;
import station.domain.user.Prefix;
import station.exception.DaoException;

public class PrefixDaoImpl extends BaseDao implements PrefixDao {

    public PrefixDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(Prefix prefix) throws DaoException {
        String query = "INSERT INTO `prefixes` (`prefix`, `city`) "
                + "VALUES (?, ?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, prefix.getId());
            statement.setString(2, prefix.getCity());
            statement.executeUpdate();
            return prefix.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public Prefix read(Integer id) throws DaoException {
        String query = "SELECT `city` FROM `prefixes` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Prefix prefix = null;
            if (resultSet.next()) {
                prefix = new Prefix();
                prefix.setId(id);
                prefix.setCity(resultSet.getString("city"));
            }
            return prefix;
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
    public void update(Prefix prefix) throws DaoException {
        String query = "UPDATE `prefixes` SET `city` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, prefix.getCity());
            statement.setInt(2, prefix.getId());
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
        String query = "DELETE FROM `prefixes` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
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
