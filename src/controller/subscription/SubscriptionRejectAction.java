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

public class SubscriptionRejectAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch(NumberFormatException e) {
            throw new ServletException(e);
        }
        
        if (id != null) {
            //TODO: проверить извлечение из сессии
            Subscriber subscriber = null;
            try {
                subscriber = 
                        new UserRetriever<Subscriber>(request).getCurrentUser();
            } catch (RetrieveException e) {
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
                throw new ServletException(e);
            }
        }
        return new Forwarder("/subscription/list.html");
    }
}
