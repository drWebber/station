package station.service;


import java.sql.Connection;

import station.dao.interfaces.user.SubscriberDao;
import station.dao.interfaces.user.UserDao;
import station.exception.FactoryException;
import station.service.interfaces.user.SubscriberService;

public interface ServiceFactory extends AutoCloseable {
    SubscriberDao getSubscriberDao() throws FactoryException;
    
    UserDao getUserDao() throws FactoryException;
    
    SubscriberService getSubscriberService() throws FactoryException;
    
    Connection getConnection() throws FactoryException;
}
