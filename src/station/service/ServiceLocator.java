package station.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.NamingException;

import station.dao.interfaces.payment.InvoiceDao;
import station.dao.interfaces.payment.PaymentDao;
import station.dao.interfaces.service.CallDao;
import station.dao.interfaces.service.CallingRateDao;
import station.dao.interfaces.service.ServicesDao;
import station.dao.mysql.payment.InvoiceDaoImpl;
import station.dao.mysql.payment.PaymentDaoImpl;
import station.dao.mysql.service.CallDaoImpl;
import station.dao.mysql.service.CallingRateDaoImpl;
import station.dao.mysql.service.ProvidedServiceDaoImpl;
import station.dao.mysql.service.ServicesDaoImpl;
import station.dao.mysql.user.AdministratorDaoImpl;
import station.dao.mysql.user.SubscriberDaoImpl;
import station.dao.mysql.user.UserDaoImpl;
import station.datasource.MysqlConnector;
import station.exception.ServiceException;
import station.service.impl.payment.InvoiceServiceImpl;
import station.service.impl.payment.PaymentServiceImpl;
import station.service.impl.service.CallServiceImpl;
import station.service.impl.service.CallingRateServiceImpl;
import station.service.impl.service.ProvidedServicesServiceImpl;
import station.service.impl.service.ServicesServiceImpl;
import station.service.impl.user.AdministratorServiceImpl;
import station.service.impl.user.SubscriberServiceImpl;
import station.service.interfaces.payment.InvoiceService;
import station.service.interfaces.payment.PaymentService;
import station.service.interfaces.service.CallService;
import station.service.interfaces.service.CallingRateService;
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
            CallingRateDao rateDao = new CallingRateDaoImpl(connection);
            CallDao callDao = new CallDaoImpl(connection);
            InvoiceDao invoiceDao = new InvoiceDaoImpl(connection);
            PaymentDao paymentDao = new PaymentDaoImpl(connection);
            
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
            CallingRateServiceImpl rateService = new CallingRateServiceImpl();
            rateService.setRateDao(rateDao);
            CallServiceImpl callService = new CallServiceImpl();
            callService.setCallDao(callDao);
            InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
            invoiceService.setInvoiceDao(invoiceDao);
            PaymentServiceImpl paymentService = new PaymentServiceImpl();
            paymentService.setPaymentDao(paymentDao);
            
            /* регистрация сервисов */
            services.put(SubscriberService.class, subscriberService);
            services.put(AdministratorService.class, administratorService);
            services.put(ProvidedServicesService.class, 
                    providedServicesService);
            services.put(ServicesService.class, servicesService);
            services.put(CallingRateService.class, rateService);
            services.put(CallService.class, callService);
            services.put(InvoiceService.class, invoiceService);
            services.put(PaymentService.class, paymentService);
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
