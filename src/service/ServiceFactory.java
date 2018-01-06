package service;


import java.sql.Connection;

import exception.FactoryException;
import service.interfaces.service.OfferService;
import service.interfaces.service.SubscriptionService;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;

public interface ServiceFactory extends AutoCloseable {
    
    AdministratorService getAdministratorService() throws FactoryException;
    
    SubscriberService getSubscriberService() throws FactoryException;
    
    PrefixService getPrefixService() throws FactoryException;
    
    OfferService getOfferService() 
            throws FactoryException;
    
    SubscriptionService getSubscriptionService() throws FactoryException;
    
    Connection getConnection() throws FactoryException;
}
