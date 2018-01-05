package controller.subscriber;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.user.SubscriberService;

public class SubscriberListAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            SubscriberService service = getServiceFactory()
                    .getSubscriberService();
            List<Subscriber> subscribers = service.getAll();
            request.setAttribute("subscribers", subscribers);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
