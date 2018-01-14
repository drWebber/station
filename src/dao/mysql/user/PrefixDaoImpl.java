package dao.mysql.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.user.PrefixDao;
import dao.mysql.BaseDao;
import domain.user.Prefix;
import exception.DaoException;

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
        String query = "SELECT `city` FROM `prefixes` WHERE `prefix` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Prefix prefix = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                prefix = new Prefix();
                prefix.setId(id);
                prefix.setCity(resultSet.getString("city"));
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
        return prefix;
    }

    @Override
    public List<Prefix> readAll() throws DaoException {
        String query = "SELECT * FROM `prefixes`";
        Statement statement = null;
        ResultSet resultSet = null;
        List<Prefix> prefixes = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Prefix prefix = new Prefix();
                prefix.setId(resultSet.getInt("prefix"));
                prefix.setCity(resultSet.getString("city"));
                prefixes.add(prefix);
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
        return prefixes;
    }

    @Override
    public void update(Prefix prefix) throws DaoException {
        String query = "UPDATE `prefixes` SET `city` = ? WHERE `prefix` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, prefix.getCity());
            statement.setInt(2, prefix.getId());
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
        String query = "DELETE FROM `prefixes` WHERE `prefix` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
    }
}
