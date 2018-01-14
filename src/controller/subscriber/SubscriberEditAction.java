package controller.subscriber;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;
import controller.Action;
import controller.Forwarder;
import domain.user.Prefix;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class SubscriberEditAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriberEditAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) { }
        try {
            if (id != null) {
                SubscriberService subscriberService =
                        getServiceFactory().getSubscriberService();
                Subscriber subscriber = subscriberService.getById(id);
                request.setAttribute("subscriber", subscriber);
            }
            PrefixService prefixService =
                    getServiceFactory().getPrefixService();
            List<Prefix> prefixes = prefixService.getAll();
            request.setAttribute("prefixes", prefixes);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
