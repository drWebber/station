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
import exception.UserIsBannedException;

public class SubscriptionAcceptAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriptionAcceptAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Forwarder forwarder;
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
            subscriptionService.validateAndSave(subscription);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (UserIsBannedException e) {
            logger.warn(e);
            forwarder = new Forwarder("/offer/list.html");
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        }

        forwarder = new Forwarder("/subscription/list.html");
        forwarder.getAttributes().put("succ_msg", "You have successfully "
                + "subscribed");
        return forwarder;
    }
}
