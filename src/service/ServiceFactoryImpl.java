package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import service.impl.service.CallingRateServiceImpl;
import service.impl.service.OfferServiceImpl;
import service.impl.service.SubscriptionServiceImpl;
import service.impl.user.AdministratorServiceImpl;
import service.impl.user.PrefixServiceImpl;
import service.impl.user.SubscriberServiceImpl;
import service.interfaces.service.CallingRateService;
import service.interfaces.service.OfferService;
import service.interfaces.service.SubscriptionService;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;
import util.MysqlConnector;
import dao.mysql.service.CallingRateDaoImpl;
import dao.mysql.service.OfferDaoImpl;
import dao.mysql.service.SubscriptionDaoImpl;
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
    public CallingRateService getCallingRateService() throws FactoryException {
        CallingRateServiceImpl service = new CallingRateServiceImpl();
        service.setRateDao(new CallingRateDaoImpl(getConnection()));
        return service;
    }
}
