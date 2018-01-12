package service;

import java.sql.Connection;

import service.interfaces.payment.InvoiceService;
import service.interfaces.payment.PaymentService;
import service.interfaces.service.CallService;
import service.interfaces.service.OfferService;
import service.interfaces.service.RateService;
import service.interfaces.service.SubscriptionService;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;
import util.transaction.Transaction;
import exception.FactoryException;

public interface ServiceFactory extends AutoCloseable {
    
    Connection getConnection() throws FactoryException;
    
    Transaction getTransaction() throws FactoryException;
    
    AdministratorService getAdministratorService() throws FactoryException;
    
    SubscriberService getSubscriberService() throws FactoryException;
    
    PrefixService getPrefixService() throws FactoryException;
    
    OfferService getOfferService() 
            throws FactoryException;
    
    SubscriptionService getSubscriptionService() throws FactoryException;

    RateService getRateService() throws FactoryException;

    CallService getCallService() throws FactoryException;

    InvoiceService getInvoiceService() throws FactoryException;

    PaymentService getPaymentService() throws FactoryException;
}
