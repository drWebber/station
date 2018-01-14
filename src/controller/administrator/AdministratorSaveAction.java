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
        boolean isCreation = false;
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            isCreation = true;
        }
                
        AdministratorValidator administratorValidator =
                new ValidatorFactoryImpl(request).getAdministratorValidator();
        
        Administrator administrator = null;
        try {
            administrator = administratorValidator.validate();
        } catch (ValidatorException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IncorrectFormDataException e) {
            Forwarder forwarder = new Forwarder("/administrator/edit.html");
            if (id != null) {
                forwarder.getAttributes().put("id", id.toString());
            }
            forwarder.getAttributes().put("message", e.getMessage());
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
        
        Forwarder forwarder = new Forwarder("/administrator/list.html");
        String message;
        if (isCreation) {
            message = "The administrator was successfully created";
        } else {
            message = "The data was successfully saved";
        }
        forwarder.getAttributes().put("message", message);
        return forwarder;
    }
}
