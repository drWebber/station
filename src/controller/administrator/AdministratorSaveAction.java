package controller.administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.AdministratorService;
import validator.ValidatorFactoryImpl;
import validator.impl.AdministratorValidator;
import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import exception.FactoryException;
import exception.IncorrectFormDataException;
import exception.LoginIsNotUnique;
import exception.ServiceException;
import exception.ValidatorException;

public class AdministratorSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(AdministratorSaveAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Forwarder forwarder = null;
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
            logger.warn(e);
            forwarder = new Forwarder("/administrator/edit.html");
            if (id != null) {
                forwarder.getAttributes().put("id", id.toString());
            }
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        }

        try {
            AdministratorService service =
                    getServiceFactory().getAdministratorService();
            service.save(administrator);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (LoginIsNotUnique e) {
            logger.info(e);
            forwarder = new Forwarder("/administrator/edit.html");
            if (id != null) {
                forwarder.getAttributes().put("id", id.toString());
            }
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        }

        forwarder = new Forwarder("/administrator/list.html");
        String message;
        if (isCreation) {
            message = "The administrator was successfully created";
        } else {
            message = "The data was successfully saved";
        }
        forwarder.getAttributes().put("succ_msg", message);
        return forwarder;
    }
}
