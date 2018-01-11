package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

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
    private Connection connection = null;
    
    @Override
    public Connection getConnection() throws FactoryException {
        if (connection == null) {
            try {
                connection = MysqlConnector.getConnection();
            } catch (NamingException | SQLException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
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
        service.setAdministratorDao(new AdministratorDaoImpl(getConnection()));
        service.setUserDao(new UserDaoImpl(getConnection()));
        return service;
    }

    @Override
    public SubscriberService getSubscriberService() throws FactoryException {
        SubscriberServiceImpl service = new SubscriberServiceImpl();
        service.setUserDao(new UserDaoImpl(getConnection()));
        service.setSubscriberDao(new SubscriberDaoImpl(getConnection()));
        return service;
    }

    @Override
    public PrefixService getPrefixService() throws FactoryException {
        PrefixServiceImpl service = new PrefixServiceImpl();
        service.setPrefixDao(new PrefixDaoImpl(getConnection()));
        return service;
    }

    @Override
    public OfferService getOfferService()
            throws FactoryException {
        OfferServiceImpl service = new OfferServiceImpl();
        service.setOfferDao(new OfferDaoImpl(getConnection()));
        return service;
    }

    @Override
    public SubscriptionService getSubscriptionService() 
            throws FactoryException {
        SubscriptionServiceImpl service = new SubscriptionServiceImpl();
        service.setServicesDao(new SubscriptionDaoImpl(getConnection()));
        service.setOfferDao(new OfferDaoImpl(getConnection()));
        return service;
    }

    @Override
    public RateService getRateService() throws FactoryException {
        RateServiceImpl service = new RateServiceImpl();
        service.setRateDao(new RateDaoImpl(getConnection()));
        return service;
    }

    @Override
    public CallService getCallService() throws FactoryException {
        CallServiceImpl service = new CallServiceImpl();
        service.setCallDao(new CallDaoImpl(getConnection()));
        return service;
    }

    @Override
    public InvoiceService getInvoiceService() throws FactoryException {
        InvoiceServiceImpl service = new InvoiceServiceImpl();
        service.setInvoiceDao(new InvoiceDaoImpl(getConnection()));
        service.setCallsDetailsDao(new CallsDetailDaoImpl(getConnection()));
        service.setSubscriptionsDetailDao(
                new SubscriptionsDetailDaoImpl(getConnection()));
        return service;
    }

    @Override
    public PaymentService getPaymentService() throws FactoryException {
        PaymentServiceImpl service = new PaymentServiceImpl();
        service.setPaymentDao(new PaymentDaoImpl(getConnection()));
        return service;
    }
}
