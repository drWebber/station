package station.controller.subscriber;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.domain.user.Administrator;
import station.domain.user.Prefix;
import station.domain.user.Subscriber;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.user.AdministratorService;
import station.service.interfaces.user.PrefixService;
import station.service.interfaces.user.SubscriberService;

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
