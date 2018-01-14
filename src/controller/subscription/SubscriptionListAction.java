package controller.subscription;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.SubscriptionService;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.RetrieveException;
import exception.ServiceException;

public class SubscriptionListAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriptionListAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            Subscriber subscriber = 
                    new UserRetriever<Subscriber>(request).getCurrentUser();
            SubscriptionService subscriptionService = 
                    getServiceFactory().getSubscriptionService();
            List<Subscription> activeSubscriptions = 
                    subscriptionService.getSubscriptions(subscriber, false);
            request.setAttribute("activeSubscriptions", activeSubscriptions);
            List<Subscription> archievedSubscriptions =
                    subscriptionService.getSubscriptions(subscriber, true);
            request.setAttribute("archievedSubscriptions", 
                    archievedSubscriptions);
        } catch (FactoryException | ServiceException | 
                RetrieveException | NullPointerException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
