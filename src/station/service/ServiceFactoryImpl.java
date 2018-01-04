package station.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import station.dao.interfaces.service.ProvidedServicesDao;
import station.dao.mysql.service.ProvidedServiceDaoImpl;
import station.dao.mysql.service.ServicesDaoImpl;
import station.dao.mysql.user.AdministratorDaoImpl;
import station.dao.mysql.user.PrefixDaoImpl;
import station.dao.mysql.user.SubscriberDaoImpl;
import station.dao.mysql.user.UserDaoImpl;
import station.domain.service.ProvidedService;
import station.exception.DaoException;
import station.exception.FactoryException;
import station.service.impl.service.ProvidedServicesServiceImpl;
import station.service.impl.service.ServicesServiceImpl;
import station.service.impl.user.AdministratorServiceImpl;
import station.service.impl.user.PrefixServiceImpl;
import station.service.impl.user.SubscriberServiceImpl;
import station.service.interfaces.service.ProvidedServicesService;
import station.service.interfaces.service.ServicesService;
import station.service.interfaces.user.AdministratorService;
import station.service.interfaces.user.PrefixService;
import station.service.interfaces.user.SubscriberService;
import station.util.MysqlConnector;

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
    public ProvidedServicesService getProvidedServicesService()
            throws FactoryException {
        ProvidedServicesServiceImpl service = new ProvidedServicesServiceImpl();
        service.setProvidedSrvDao(new ProvidedServiceDaoImpl(getConnection()));
        return service;
    }

    @Override
    public ServicesService getServicesService() throws FactoryException {
        ServicesServiceImpl service = new ServicesServiceImpl();
        service.setServicesDao(new ServicesDaoImpl(getConnection()));
        service.setProvidedSrvDao(new ProvidedServiceDaoImpl(getConnection()));
        return service;
    }
}
