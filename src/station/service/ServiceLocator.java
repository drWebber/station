package station.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.NamingException;

import station.dao.interfaces.service.ServicesDao;
import station.dao.mysql.service.ProvidedServiceDaoImpl;
import station.dao.mysql.service.ServicesDaoImpl;
import station.dao.mysql.user.AdministratorDaoImpl;
import station.dao.mysql.user.SubscriberDaoImpl;
import station.dao.mysql.user.UserDaoImpl;
import station.datasource.MysqlConnector;
import station.exception.ServiceException;
import station.service.impl.service.ProvidedServicesServiceImpl;
import station.service.impl.service.ServicesServiceImpl;
import station.service.impl.user.AdministratorServiceImpl;
import station.service.impl.user.SubscriberServiceImpl;
import station.service.interfaces.service.ProvidedServicesService;
import station.service.interfaces.service.ServicesService;
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
            ProvidedServiceDaoImpl providedServicesDao = 
                    new ProvidedServiceDaoImpl(connection);
            ServicesDao servicesDao = new ServicesDaoImpl(connection);
            
            /* создание объектов слоя сервисов */
            SubscriberServiceImpl subscriberService = 
                    new SubscriberServiceImpl();
            subscriberService.setSubscriberDao(subscriberDao);
            subscriberService.setUserDao(userDao);
            AdministratorServiceImpl administratorService =
                    new AdministratorServiceImpl();
            administratorService.setAdministratorDao(administratorDao);
            administratorService.setUserDao(userDao);
            ProvidedServicesServiceImpl providedServicesService =
                    new ProvidedServicesServiceImpl();
            providedServicesService.setProvidedSrvDao(providedServicesDao);
            ServicesServiceImpl servicesService = new ServicesServiceImpl();
            servicesService.setServicesDao(servicesDao);
            
            /* регистрация сервисов */
            services.put(SubscriberService.class, subscriberService);
            services.put(AdministratorService.class, administratorService);
            services.put(ProvidedServicesService.class, 
                    providedServicesService);
            services.put(ServicesService.class, servicesService);
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
