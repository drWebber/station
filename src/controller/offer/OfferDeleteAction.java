package controller.offer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.service.OfferService;

public class OfferDeleteAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException e) { }
        
        if (id != null) {
            try {
                OfferService service = 
                        getServiceFactory().getOfferService();
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forwarder("/offer/list.html");
    }
}
