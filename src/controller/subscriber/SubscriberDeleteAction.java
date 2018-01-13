package controller.subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.SubscriberService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;

public class SubscriberDeleteAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriberDeleteAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
            SubscriberService service =
                    getServiceFactory().getSubscriberService();
            service.delete(id);
        } catch(NumberFormatException | FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return new Forwarder("/subscriber/list.html");
    }
}
