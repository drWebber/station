package dao.mysql.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public void createInvoices() throws DaoException {
        String query = ""
                + "INSERT INTO `invoices` (`subscriberID`, `invoicingDate`, "
                    + "`amount`) "
                + "SELECT t.`subscriberID`, CURRENT_TIMESTAMP, SUM(t.`sum`) "
                    + "AS total "
                + "FROM "
                    + "(SELECT * FROM vw_mon_calls_cost "
                    + "UNION "
                    + "SELECT * FROM vw_mon_subscriptions_cost "
                    + "UNION "
                    + "SELECT * FROM vw_mon_srv_fee "
                    + "UNION "
                    + "SELECT * FROM vw_mon_major_fee) AS t "
                + "GROUP BY `subscriberID`";
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
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
            statement.setBigDecimal(3, invoice.getAmount());
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
        String query = "SELECT * FROM `invoices` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Invoice invoice = null;
            if (resultSet.next()) {
                invoice = getInvoice(resultSet);
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

    private Invoice getInvoice(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(resultSet.getLong("id"));
        Subscriber subscriber = new Subscriber();
        subscriber.setId(resultSet.getLong("subscriberID"));
        invoice.setSubscriber(subscriber);
        invoice.setInvoicingDate(resultSet
                .getTimestamp("invoicingDate"));
        invoice.setAmount(resultSet.getBigDecimal("amount"));
        return invoice;
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
            statement.setBigDecimal(3, invoice.getAmount());
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

    @Override
    public void deleteAll() throws DaoException {
        String query = "DELETE FROM `invoices` WHERE 1";
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate(query);
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {}
        }
    }

    @Override
    public boolean canCreate() throws DaoException {
        String query = "SELECT `id` "
                + "FROM `invoices` "
                + "WHERE `invoicingDate` >= LAST_DAY(CURDATE()) + "
                    + "INTERVAL 1 DAY - INTERVAL 1 MONTH "
                + "LIMIT 1";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet.next() ? false : true;
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
    public List<Invoice> readUnpaid() throws DaoException {
        String query = "" 
                + "SELECT *"
                + "FROM `invoices` "
                + "WHERE `id` NOT IN(SELECT `invoiceID` FROM `payments`)"
                + "ORDER BY `subscriberID`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            List<Invoice> unpaid = new ArrayList<>();
            while (resultSet.next()) {
                unpaid.add(getInvoice(resultSet));
            }
            return unpaid;
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
    public List<Invoice> readInvoices(Subscriber subscriber, boolean isPaid)
            throws DaoException {
        String operator = "";
        if (!isPaid) {
            operator += "NOT";
        }
        String query = String.format(""
                + "SELECT * FROM `invoices` "
                    + "WHERE `subscriberID` = ? AND `id` %s IN"
                        + "(SELECT `invoiceID` FROM `payments`) "
                    + "ORDER BY `invoicingDate`", operator);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, subscriber.getId());
            resultSet = statement.executeQuery();
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                invoices.add(getInvoice(resultSet));
            }
            return invoices;
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
