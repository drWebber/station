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
import exception.service.ServiceException;

public class SubscriptionRejectAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriptionRejectAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }

        if (id != null) {
            Subscriber subscriber = null;
            try {
                subscriber =
                        new UserRetriever<Subscriber>(request).getCurrentUser();
            } catch (RetrieveException e) {
                logger.error("RetrieveException", e);
                throw new ServletException(e);
            }
            try {
                SubscriptionService service =
                        getServiceFactory().getSubscriptionService();
                Subscription subscription = new Subscription();
                subscription.setId(id);
                subscription.setSubscriber(subscriber);
                service.save(subscription);
            } catch (FactoryException | ServiceException e) {
                logger.error("Reject exception", e);
                throw new ServletException(e);
            }
        }

        Forwarder forwarder = new Forwarder("/subscription/list.html");
        forwarder.getAttributes().put("succ_msg", "You have been successfully "
                + "unsubscribed");
        return forwarder;
    }
}
