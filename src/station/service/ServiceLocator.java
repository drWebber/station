package station.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.NamingException;

import station.dao.mysql.user.AdministratorDaoImpl;
import station.dao.mysql.user.SubscriberDaoImpl;
import station.dao.mysql.user.UserDaoImpl;
import station.datasource.MysqlConnector;
import station.exception.ServiceException;
import station.service.impl.user.AdministratorServiceImpl;
import station.service.impl.user.SubscriberServiceImpl;
import station.service.interfaces.user.AdministratorService;
import station.service.interfaces.user.SubscriberService;

public class ServiceLocator {
    private Map<Class<?>, Object> services = new ConcurrentHashMap<>();
    private Connection connection;
    
    public ServiceLocator() throws ServiceException {
        try {
            connection = MysqlConnector.getConnection();
            
            /* создание объектов слоя Data Access Objects */
            UserDaoImpl userDao = new UserDaoImpl(connection);
            SubscriberDaoImpl subscriberDao = new SubscriberDaoImpl(connection);
            AdministratorDaoImpl administratorDao = 
                    new AdministratorDaoImpl(connection);
            
            /* создание объектов слоя сервисов */
            SubscriberServiceImpl subscriberService = 
                    new SubscriberServiceImpl();
            subscriberService.setSubscriberDao(subscriberDao);
            subscriberService.setUserDao(userDao);
            AdministratorServiceImpl administratorService =
                    new AdministratorServiceImpl();
            administratorService.setAdministratorDao(administratorDao);
            administratorService.setUserDao(userDao);
            
            /* регистрация сервисов */
            services.put(SubscriberService.class, subscriberService);
            services.put(AdministratorService.class, administratorService);
        } catch (NamingException | SQLException e) {
            throw new  ServiceException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) {
        return (T) services.get(serviceClass);
    }
    
    public void close() throws ServiceException {
        try {
            connection.close();
        } catch(SQLException e) {
            throw new ServiceException(e);
        }
    }
}
