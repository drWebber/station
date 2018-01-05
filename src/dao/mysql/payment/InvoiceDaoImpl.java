package dao.mysql.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaces.payment.InvoiceDao;
import dao.mysql.BaseDao;
import domain.payment.Invoice;
import domain.user.Subscriber;
import exception.DaoException;

public class InvoiceDaoImpl extends BaseDao implements InvoiceDao {

    public InvoiceDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Invoice invoice) throws DaoException {
        String query = "INSERT INTO `invoices` (`subscriberID`, "
                + "`invoicingDate`, `amount`) VALUES(?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, invoice.getSubscriber().getId());
            statement.setTimestamp(2, invoice.getInvoicingDate());
            statement.setFloat(3, invoice.getAmount());
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
    public Invoice read(Long id) throws DaoException {
        String query = "SELECT `subscriberID`, `invoicingDate`, `amount` "
                + "FROM `invoices` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Invoice invoice = null;
            if (resultSet.next()) {
                invoice = new Invoice();
                invoice.setId(id);
                Subscriber subscriber = new Subscriber();
                subscriber.setId(resultSet.getLong("subscriberID"));
                invoice.setSubscriber(subscriber);
                invoice.setInvoicingDate(resultSet
                        .getTimestamp("invoicingDate"));
                invoice.setAmount(resultSet.getFloat("amount"));
            }
            return invoice;
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
    public void update(Invoice invoice) throws DaoException {
        String query = "UPDATE `invoices` SET `subscriberID` = ?, "
                + "`invoicingDate` = ?, `amount` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, invoice.getSubscriber().getId());
            statement.setTimestamp(2, invoice.getInvoicingDate());
            statement.setFloat(3, invoice.getAmount());
            statement.setLong(4, invoice.getId());
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
        String query = "DELETE FROM `invoices` WHERE `id` = ?";
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
