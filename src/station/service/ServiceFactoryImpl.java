package station.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import station.dao.interfaces.user.SubscriberDao;
import station.dao.interfaces.user.UserDao;
import station.dao.mysql.user.SubscriberDaoImpl;
import station.dao.mysql.user.UserDaoImpl;
import station.exception.FactoryException;
import station.service.impl.user.SubscriberServiceImpl;
import station.service.interfaces.user.SubscriberService;
import station.util.MysqlConnector;

public class ServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public SubscriberService getSubscriberService() throws FactoryException {
        SubscriberServiceImpl subscriberService = new SubscriberServiceImpl();
        subscriberService.setUserDao(getUserDao());
        subscriberService.setSubscriberDao(getSubscriberDao());
        return subscriberService;
    }
    
    @Override
    public SubscriberDao getSubscriberDao() throws FactoryException {
        return new SubscriberDaoImpl(getConnection());
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public Connection getConnection() throws FactoryException {
        try {
            connection = MysqlConnector.getConnection();
        } catch (NamingException | SQLException e) {
            throw new FactoryException(e);
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
}
