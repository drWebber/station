package controller.subscriber;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.SubscriberService;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import domain.user.Role;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.RetrieveException;
import exception.ServiceException;

public class SubscriberSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriberSaveAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Subscriber subscriber = new Subscriber();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            subscriber.getUser().setId(id);
            subscriber.setId(id);
        } catch(NumberFormatException e) { }
        if (subscriber.getId() == null) {
            subscriber.getUser().setLogin(request.getParameter("login"));
            subscriber.getUser().setPassword(request.getParameter("password"));
            try {
                subscriber.getUser().cryptPassword();
            } catch (NoSuchAlgorithmException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        }
        subscriber.getUser().setSurname(request.getParameter("surname"));
        subscriber.getUser().setName(request.getParameter("name"));
        subscriber.getUser().setPatronymic(request.getParameter("patronymic"));
        subscriber.getUser().setRole(Role.SUBSCRIBER);
        subscriber.getUser().setActive(
                Boolean.parseBoolean(request.getParameter("isActive")));
        subscriber.setPassportId(request.getParameter("passportId"));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(request.getParameter("birthday"));
            subscriber.setBirthDay(new java.sql.Date(date.getTime()));
        } catch (ParseException e) {
            logger.warn(e);
            throw new ServletException(e);
        }
        Integer phoneNum = null;
        Integer prefixId = null;
        try {
            phoneNum = Integer.parseInt(request.getParameter("phoneNum"));
            prefixId = Integer.parseInt(request.getParameter("prefix"));
        } catch(NumberFormatException e) {
            logger.warn(e);
            throw new ServletException(e); 
        }
        
        subscriber.setAddress(request.getParameter("address"));
        subscriber.getPrefix().setId(prefixId);
        subscriber.setPhoneNum(phoneNum);
        
        Administrator administrator = null;
        try {
            administrator = 
                    new UserRetriever<Administrator>(request).getCurrentUser();
        } catch (RetrieveException e) {
            new ServletException(e);
        }
        subscriber.setAdministrator(administrator);
        
        try {
            SubscriberService service = getServiceFactory().getSubscriberService();
            service.save(subscriber);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        return new Forwarder("/subscriber/list.html");
    }
}
