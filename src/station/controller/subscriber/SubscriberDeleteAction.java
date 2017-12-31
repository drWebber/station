package station.controller.subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.user.SubscriberService;

public class SubscriberDeleteAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch(NumberFormatException e) { }
        
        if (id != null) {
            try {
                SubscriberService service = getServiceFactory().getSubscriberService();
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forwarder("/subscriber/list.html");
    }
}
