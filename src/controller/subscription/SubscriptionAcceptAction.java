package controller.subscription;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.SubscriptionService;
import util.user.RetrieveException;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class SubscriptionAcceptAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Integer offerId = null;
        try {
            offerId = Integer.parseInt(request.getParameter("offerId"));
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        
        //TODO: проверить извлечение из сессии
        Subscriber subscriber = null;
        try {
            subscriber = 
                    new UserRetriever<Subscriber>(request).getCurrentUser();
        } catch (RetrieveException e) {
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
            throw new ServletException(e);
        }
        return new Forwarder("/subscription/list.html");
    }
}
