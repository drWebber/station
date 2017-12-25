package station.dao.mysql.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import station.dao.interfaces.payment.PaymentDao;
import station.dao.mysql.BaseDao;
import station.domain.payment.Invoice;
import station.domain.payment.Payment;
import station.exception.DaoException;

public class PaymentDaoImpl extends BaseDao implements PaymentDao {
    public PaymentDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Payment payment) throws DaoException {
        String query = "INSERT INTO `payments` (`date`, `bankCode`, "
                + "`invoiceID`) VALUES(?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, payment.getDate());
            statement.setString(2, payment.getBankCode());
            statement.setLong(3, payment.getInvoice().getId());
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
    public Payment read(Long id) throws DaoException {
        String query = "SELECT `date`, `bankCode`, `invoiceID` FROM `payments` "
                + "WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Payment payment = null;
            if (resultSet.next()) {
                payment = new Payment();
                payment.setId(id);
                payment.setDate(resultSet.getTimestamp("date"));
                payment.setBankCode(resultSet.getString("bankCode"));
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getLong("invoiceID"));
                payment.setInvoice(invoice);
            }
            return payment;
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
}
