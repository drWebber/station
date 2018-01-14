package dao.mysql.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.payment.PaymentDao;
import dao.mysql.BaseDao;
import domain.payment.Invoice;
import domain.payment.Payment;
import domain.user.Subscriber;
import exception.DaoException;

public class PaymentDaoImpl extends BaseDao implements PaymentDao {
    public PaymentDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Payment payment) throws DaoException {
        String query = "INSERT INTO `payments` (`invoiceID`) VALUES(?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, payment.getInvoice().getId());
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
        String query = "SELECT * FROM `payments` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Payment payment = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment = getPayment(resultSet);
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

    @Override
    public List<Payment> readBySubscriber(Subscriber subscriber)
            throws DaoException {
        String query = "SELECT * "
                     + "FROM `payments` "
                     + "WHERE `invoiceID` IN"
                         + "(SELECT `id`"
                         + "FROM `invoices`"
                         + "WHERE `subscriberID` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            resultSet = statement.executeQuery();
            List<Payment> payments = new ArrayList<>();
            while (resultSet.next()) {
                payments.add(getPayment(resultSet));
            }
            return payments;
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

    private Payment getPayment(ResultSet resultSet) throws SQLException {
        Payment payment = new Payment();
        payment.setId(resultSet.getLong("id"));
        payment.setDate(resultSet.getTimestamp("date"));
        Invoice invoice = new Invoice();
        invoice.setId(resultSet.getLong("invoiceID"));
        payment.setInvoice(invoice);
        return payment;
    }

    @Override
    public void deleteAll() throws DaoException {
        String query = "DELETE FROM `payments` WHERE 1";
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) { }
        }
    }
}
