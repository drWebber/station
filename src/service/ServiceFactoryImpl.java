package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.impl.payment.InvoiceServiceImpl;
import service.impl.payment.PaymentServiceImpl;
import service.impl.service.CallServiceImpl;
import service.impl.service.OfferServiceImpl;
import service.impl.service.RateServiceImpl;
import service.impl.service.SubscriptionServiceImpl;
import service.impl.user.AdministratorServiceImpl;
import service.impl.user.PrefixServiceImpl;
import service.impl.user.SubscriberServiceImpl;
import service.interfaces.payment.InvoiceService;
import service.interfaces.payment.PaymentService;
import service.interfaces.service.CallService;
import service.interfaces.service.OfferService;
import service.interfaces.service.RateService;
import service.interfaces.service.SubscriptionService;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;
import util.MysqlConnector;
import util.transaction.Transaction;
import util.transaction.TransactionImpl;
import controller.ServletDispatcher;
import dao.mysql.payment.InvoiceDaoImpl;
import dao.mysql.payment.PaymentDaoImpl;
import dao.mysql.service.CallDaoImpl;
import dao.mysql.service.CallsDetailDaoImpl;
import dao.mysql.service.OfferDaoImpl;
import dao.mysql.service.RateDaoImpl;
import dao.mysql.service.SubscriptionDaoImpl;
import dao.mysql.service.SubscriptionsDetailDaoImpl;
import dao.mysql.user.AdministratorDaoImpl;
import dao.mysql.user.PrefixDaoImpl;
import dao.mysql.user.SubscriberDaoImpl;
import dao.mysql.user.UserDaoImpl;
import exception.FactoryException;

public class ServiceFactoryImpl implements ServiceFactory {
    private static Logger logger =
            LogManager.getLogger(ServletDispatcher.class.getName());
    private Connection connection = null;

    @Override
    public Connection getConnection() throws FactoryException {
        if (connection == null) {
            try {
                connection = MysqlConnector.getConnection();
            } catch (NamingException | SQLException e) {
                logger.error(e);
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public Transaction getTransaction() throws FactoryException {
        return new TransactionImpl(getConnection());
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) { }
    }

    @Override
    public AdministratorService getAdministratorService()
            throws FactoryException {
        AdministratorServiceImpl service = new AdministratorServiceImpl();
        service.setTransaction(getTransaction());
        service.setAdministratorDao(new AdministratorDaoImpl(getConnection()));
        service.setUserDao(new UserDaoImpl(getConnection()));
        return service;
    }

    @Override
    public SubscriberService getSubscriberService() throws FactoryException {
        SubscriberServiceImpl service = new SubscriberServiceImpl();
        service.setTransaction(getTransaction());
        service.setUserDao(new UserDaoImpl(getConnection()));
        service.setSubscriberDao(new SubscriberDaoImpl(getConnection()));
        service.setAdministratorDao(new AdministratorDaoImpl(getConnection()));
        return service;
    }

    @Override
    public PrefixService getPrefixService() throws FactoryException {
        PrefixServiceImpl service = new PrefixServiceImpl();
        service.setTransaction(getTransaction());
        service.setPrefixDao(new PrefixDaoImpl(getConnection()));
        return service;
    }

    @Override
    public OfferService getOfferService()
            throws FactoryException {
        OfferServiceImpl service = new OfferServiceImpl();
        service.setTransaction(getTransaction());
        service.setTransaction(getTransaction());
        service.setOfferDao(new OfferDaoImpl(getConnection()));
        return service;
    }

    @Override
    public SubscriptionService getSubscriptionService()
            throws FactoryException {
        SubscriptionServiceImpl service = new SubscriptionServiceImpl();
        service.setTransaction(getTransaction());
        service.setServicesDao(new SubscriptionDaoImpl(getConnection()));
        service.setOfferDao(new OfferDaoImpl(getConnection()));
        service.setUserDao(new UserDaoImpl(getConnection()));
        return service;
    }

    @Override
    public RateService getRateService() throws FactoryException {
        RateServiceImpl service = new RateServiceImpl();
        service.setTransaction(getTransaction());
        service.setRateDao(new RateDaoImpl(getConnection()));
        return service;
    }

    @Override
    public CallService getCallService() throws FactoryException {
        CallServiceImpl service = new CallServiceImpl();
        service.setTransaction(getTransaction());
        service.setCallDao(new CallDaoImpl(getConnection()));
        service.setRateDao(new RateDaoImpl(getConnection()));
        service.setUserDao(new UserDaoImpl(getConnection()));
        return service;
    }

    @Override
    public InvoiceService getInvoiceService() throws FactoryException {
        InvoiceServiceImpl service = new InvoiceServiceImpl();
        service.setTransaction(getTransaction());
        service.setInvoiceDao(new InvoiceDaoImpl(getConnection()));
        service.setCallsDetailsDao(new CallsDetailDaoImpl(getConnection()));
        service.setSubscriptionsDetailDao(
                new SubscriptionsDetailDaoImpl(getConnection()));
        return service;
    }

    @Override
    public PaymentService getPaymentService() throws FactoryException {
        PaymentServiceImpl service = new PaymentServiceImpl();
        service.setTransaction(getTransaction());
        service.setPaymentDao(new PaymentDaoImpl(getConnection()));
        service.setInvoiceDao(new InvoiceDaoImpl(getConnection()));
        return service;
    }
}
