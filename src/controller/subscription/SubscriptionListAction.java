package controller.subscription;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.SubscriptionService;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.service.Subscription;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class SubscriptionListAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            //TODO: проверить извлечение из сессии
            Subscriber subscriber = 
                    new UserRetriever<Subscriber>(request).getCurrentUser();
            SubscriptionService subscriptionService = 
                    getServiceFactory().getSubscriptionService();
            List<Subscription> subscriptions = 
                    subscriptionService.getSubscriptions(subscriber);
            request.setAttribute("subscriptions", subscriptions);
        } catch (FactoryException | ServiceException | ClassCastException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
