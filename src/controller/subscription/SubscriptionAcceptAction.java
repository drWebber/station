package controller.subscription;

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

public class SubscriptionAcceptAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriptionAcceptAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Integer offerId = null;
        try {
            offerId = Integer.parseInt(request.getParameter("offerId"));
        } catch (NumberFormatException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        Subscriber subscriber = null;
        try {
            subscriber = 
                    new UserRetriever<Subscriber>(request).getCurrentUser();
        } catch (RetrieveException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        Subscription subscription = new Subscription();
        subscription.setSubscriber(subscriber);
        subscription.getOffer().setId(offerId);
        try {
            SubscriptionService subscriptionService = 
                    getServiceFactory().getSubscriptionService();
            subscriptionService.save(subscription);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return new Forwarder("/subscription/list.html");
    }
}
