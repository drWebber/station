package controller.offer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.OfferService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.service.ServiceException;

public class OfferDeleteAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(OfferDeleteAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            logger.warn(e);
        }

        if (id != null) {
            try {
                OfferService service =
                        getServiceFactory().getOfferService();
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                logger.error("Deleting exception", e);
                throw new ServletException(e);
            }
        }
        Forwarder forwarder = new Forwarder("/offer/list.html");
        forwarder.getAttributes().put("succ_msg", "Record has been "
                + "successfully deleted");
        return forwarder;
    }
}
