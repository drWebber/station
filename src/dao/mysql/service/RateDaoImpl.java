package dao.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.service.RateDao;
import dao.mysql.BaseDao;
import domain.service.Rate;
import domain.service.RateType;
import exception.DaoException;

public class RateDaoImpl extends BaseDao implements RateDao {
    public RateDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Rate rate) throws DaoException {
        String query = "INSERT INTO `rates` (`type`, `tariff`) "
                + "VALUES(?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, rate.getType().toString());
            statement.setFloat(2, rate.getTariff());
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
    public Rate read(Long id) throws DaoException {
        String query = "SELECT * FROM `rates` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Rate rate = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rate = getCallingRate(resultSet);
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
        return rate;
    }

    @Override
    public Rate readCurrentByType(RateType rateType) throws DaoException {
        String query = "SELECT * FROM `rates` WHERE `type` = ? "
                + "ORDER BY `introdutionDate` DESC LIMIT 1";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Rate rate = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, rateType.toString());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rate = getCallingRate(resultSet);
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
        return rate;
    }

    @Override
    public List<Rate> readCurrentRates() throws DaoException {
        String query = ""
                + "(SELECT * FROM `rates` WHERE `type` = 'LOCAL_OUTGOING' "
                    + "ORDER BY `introdutionDate` DESC LIMIT 1) "
                + "UNION "
                + "(SELECT * FROM `rates` WHERE `type` = 'LOCAL_INCOMING' "
                    + "ORDER BY `introdutionDate` DESC LIMIT 1) "
                + "UNION "
                + "(SELECT * FROM `rates` WHERE `type` = "
                    + "'LONG_DISTANCE_OUTGOING' "
                    + "ORDER BY `introdutionDate` DESC LIMIT 1) "
                + "UNION "
                + "(SELECT * FROM `rates` WHERE `type` = "
                    + "'LONG_DISTANCE_INCOMING' "
                    + "ORDER BY `introdutionDate` DESC LIMIT 1) "
                + "UNION "
                + "(SELECT * FROM `rates` WHERE `type` = 'MOBILE_OUTGOING' "
                    + "ORDER BY `introdutionDate` DESC LIMIT 1) "
                + "UNION "
                + "(SELECT * FROM `rates` WHERE `type` = 'MOBILE_INCOMING' "
                    + "ORDER BY `introdutionDate` DESC LIMIT 1)";
        Statement statement = null;
        ResultSet resultSet = null;
        List<Rate> rates = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                rates.add(getCallingRate(resultSet));
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
        return rates;
    }

    private Rate getCallingRate(final ResultSet resultSet)
            throws SQLException {
        Rate rate = new Rate();
        rate.setId(resultSet.getLong("id"));
        rate.setType(RateType.valueOf(resultSet.getString("type")));
        rate.setIntrodutionDate(resultSet.getTimestamp("introdutionDate"));
        rate.setTariff(resultSet.getFloat("tariff"));
        return rate;
    }
}
