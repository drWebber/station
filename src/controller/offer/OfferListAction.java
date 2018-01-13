package controller.offer;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.OfferService;
import controller.Action;
import controller.Forwarder;
import domain.service.Offer;
import exception.FactoryException;
import exception.ServiceException;

public class OfferListAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(OfferListAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            OfferService offerservice = getServiceFactory()
                    .getOfferService();
            List<Offer> additionalOffers = 
                    offerservice.getByRequirement(false);
            request.setAttribute("additionalOffers", additionalOffers);
            List<Offer> requiredOffers = 
                    offerservice.getByRequirement(true);
            request.setAttribute("requiredOffers", requiredOffers);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
