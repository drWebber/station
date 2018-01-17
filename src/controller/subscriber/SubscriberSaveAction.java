package controller.subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.SubscriberService;
import validator.ValidatorFactoryImpl;
import validator.impl.SubscriberValidator;
import controller.Action;
import controller.Forwarder;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.service.LoginIsNotUnique;
import exception.service.PassportIdIsNotUnique;
import exception.service.PhoneIsNotUnique;
import exception.service.ServiceException;
import exception.validator.IncorrectFormDataException;
import exception.validator.ValidatorException;

public class SubscriberSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriberSaveAction.class.getName());

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

        Subscriber subscriber = null;
        try {
            SubscriberValidator validator =
                    new ValidatorFactoryImpl(request).getSubscriberValidator();
            subscriber = validator.validate();
        } catch (ValidatorException e) {
            logger.error("ValidatorException", e);
            new ServiceException(e);
        } catch (IncorrectFormDataException e) {
            logger.warn(e);
            forwarder = new Forwarder("/subscriber/edit.html");
            if (id != null) {
                forwarder.getAttributes().put("id", id.toString());
            }
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        }

        try {
            SubscriberService service =
                    getServiceFactory().getSubscriberService();
            service.save(subscriber);
        } catch (PhoneIsNotUnique | LoginIsNotUnique
                | PassportIdIsNotUnique e) {
            logger.info(e);
            forwarder = new Forwarder("/subscriber/edit.html");
            if (id != null) {
                forwarder.getAttributes().put("id", id.toString());
            }
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        } catch (FactoryException | ServiceException e) {
            logger.error("Save exception", e);
            throw new ServletException(e);
        }

        forwarder = new Forwarder("/subscriber/list.html");
        String message;
        if (isCreation) {
            message = "The subscriber was successfully created";
        } else {
            message = "The data was successfully saved";
        }
        forwarder.getAttributes().put("succ_msg", message);
        return forwarder;
    }
}
