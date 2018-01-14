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

public class OfferEditAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(OfferEditAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));            
        } catch (NumberFormatException e) { }
        
        try {
            if (id != null) {
                OfferService offerService =
                        getServiceFactory().getOfferService();
                Offer offer = 
                        offerService.getById(id);
                request.setAttribute("offer", offer);
            }
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
