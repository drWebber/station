package controller.offer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.OfferService;
import validator.ValidatorFactoryImpl;
import validator.impl.OfferValidator;
import controller.Action;
import controller.Forwarder;
import domain.service.Offer;
import exception.FactoryException;
import exception.IncorrectFormDataException;
import exception.ServiceException;
import exception.ValidatorException;

public class OfferSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(OfferSaveAction.class.getName());
    
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
        
        Offer offer = null;
        try {
            OfferValidator offerValidator =
                    new ValidatorFactoryImpl(request).getOfferValidator();
            offer = offerValidator.validate();
        } catch (ValidatorException e) {
            logger.error(e);
        } catch (IncorrectFormDataException e) {
            logger.warn(e);
            forwarder = new Forwarder("/offer/edit.html");
            if (id != null) {
                forwarder.getAttributes().put("id", id.toString());
            }
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        }
        
        try {
            OfferService offerService = getServiceFactory().getOfferService();
            offerService.save(offer);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        String message;
        if (isCreation) {
            message = "The offer was successfully created";
        } else {
            message = "The data was successfully saved";
        }
        forwarder = new Forwarder("/offer/list.html");
        forwarder.getAttributes().put("succ_msg", message);
        
        return forwarder;
    }
}
