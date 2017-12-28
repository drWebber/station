package station.controller.subscriber;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.domain.user.Subscriber;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.user.SubscriberService;

public class SubscriberListAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            SubscriberService service = getServiceFactory()
                    .getSubscriberService();
            List<Subscriber> subscribers = service.getAll();
            request.setAttribute("subscribers", subscribers);
            return null;
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
