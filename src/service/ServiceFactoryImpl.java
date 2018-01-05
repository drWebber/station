package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import dao.interfaces.service.ProvidedServicesDao;
import dao.mysql.service.ProvidedServiceDaoImpl;
import dao.mysql.service.ServicesDaoImpl;
import dao.mysql.user.AdministratorDaoImpl;
import dao.mysql.user.PrefixDaoImpl;
import dao.mysql.user.SubscriberDaoImpl;
import dao.mysql.user.UserDaoImpl;
import domain.service.ProvidedService;
import exception.DaoException;
import exception.FactoryException;
import service.impl.service.ProvidedServicesServiceImpl;
import service.impl.service.ServicesServiceImpl;
import service.impl.user.AdministratorServiceImpl;
import service.impl.user.PrefixServiceImpl;
import service.impl.user.SubscriberServiceImpl;
import service.interfaces.service.ProvidedServicesService;
import service.interfaces.service.ServicesService;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;
import util.MysqlConnector;

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
