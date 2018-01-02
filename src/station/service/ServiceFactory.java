package station.service;


import java.sql.Connection;

import station.exception.FactoryException;
import station.service.interfaces.service.ProvidedServicesService;
import station.service.interfaces.user.AdministratorService;
import station.service.interfaces.user.PrefixService;
import station.service.interfaces.user.SubscriberService;

public interface ServiceFactory extends AutoCloseable {
    
    AdministratorService getAdministratorService() throws FactoryException;
    
    SubscriberService getSubscriberService() throws FactoryException;
    
    PrefixService getPrefixService() throws FactoryException;
    
    ProvidedServicesService getProvidedServicesService() 
            throws FactoryException;
    
    Connection getConnection() throws FactoryException;
}
