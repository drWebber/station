package controller.offer;

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

public class OfferSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(OfferSaveAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Offer offer = new Offer();
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            offer.setId(id);
        } catch(NumberFormatException e) { }
        offer.setName(request.getParameter("name"));
        offer.setDescription(request.getParameter("description"));
        
        try {
            Float monthlyFee = 
                    Float.parseFloat(request.getParameter("monthlyFee"));
            Float subscriptionRate = 
                    Float.parseFloat(request.getParameter("subscriptionRate")); 
            offer.setMonthlyFee(monthlyFee);
            offer.setSubscriptionRate(subscriptionRate);
        } catch (NumberFormatException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        offer.setRequired(
                Boolean.parseBoolean(request.getParameter("required")));
        
        try {
            OfferService offerService = getServiceFactory().getOfferService();
            offerService.save(offer);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        return new Forwarder("/offer/list.html");
    }
}
