package controller.offer;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import domain.service.Offer;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.service.OfferService;

public class OfferSaveAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) { }
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
            throw new ServletException(e);
        }
        
        offer.setRequired(
                Boolean.parseBoolean(request.getParameter("required")));
        
        try {
            OfferService offerService = getServiceFactory().getOfferService();
            offerService.save(offer);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        
        return new Forwarder("/offer/list.html");
    }
}
