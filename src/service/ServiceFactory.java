package service;


import java.sql.Connection;

import exception.FactoryException;
import service.interfaces.service.ProvidedServicesService;
import service.interfaces.service.ServicesService;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;

public interface ServiceFactory extends AutoCloseable {
    
    AdministratorService getAdministratorService() throws FactoryException;
    
    SubscriberService getSubscriberService() throws FactoryException;
    
    PrefixService getPrefixService() throws FactoryException;
    
    ProvidedServicesService getProvidedServicesService() 
            throws FactoryException;
    
    ServicesService getServicesService() throws FactoryException;
    
    Connection getConnection() throws FactoryException;
}
