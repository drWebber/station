package controller.administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.AdministratorService;
import validator.AdministratorValidator;
import validator.ValidatorFactoryImpl;
import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import exception.FactoryException;
import exception.IncorrectFormDataException;
import exception.ServiceException;
import exception.ValidatorException;

public class AdministratorSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(AdministratorSaveAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        AdministratorValidator administratorValidator =
                new ValidatorFactoryImpl(request).getAdministratorValidator();
        Administrator administrator = null;
        try {
            administrator = administratorValidator.validate();
        } catch (ValidatorException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IncorrectFormDataException e) {
            String referer = request.getHeader("referer");
            String context = request.getContextPath();
            referer = referer.substring(referer.indexOf(context)+ context.length(), referer.length());
            Forwarder forwarder = new Forwarder(referer);
            forwarder.getAttributes().put("message", e);
            return forwarder;
        }
        
        try {
            AdministratorService service =
                    getServiceFactory().getAdministratorService();
            service.save(administrator);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        return new Forwarder("/administrator/list.html");
    }
}
