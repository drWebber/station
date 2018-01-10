package controller.subscriber;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import domain.user.Prefix;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.PrefixService;
import service.interfaces.user.SubscriberService;

public class SubscriberEditAction extends Action {
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

                AdministratorService administratorService =
                        getServiceFactory().getAdministratorService();
                Administrator administrator =
                        administratorService.getById(subscriber
                                .getAdministrator().getId());
                subscriber.setAdministrator(administrator);
                request.setAttribute("subscriber", subscriber);
            }
            PrefixService prefixService = 
                    getServiceFactory().getPrefixService();
            List<Prefix> prefixes = prefixService.getAll();
            request.setAttribute("prefixes", prefixes);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
