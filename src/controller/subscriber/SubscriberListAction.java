package controller.subscriber;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.SubscriberService;
import controller.Action;
import controller.Forwarder;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class SubscriberListAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriberListAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            SubscriberService service = getServiceFactory()
                    .getSubscriberService();
            List<Subscriber> subscribers = service.getAll();
            request.setAttribute("subscribers", subscribers);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
