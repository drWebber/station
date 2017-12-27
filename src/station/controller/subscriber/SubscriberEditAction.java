package station.controller.subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.domain.user.Subscriber;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.user.SubscriberService;

public class SubscriberEditAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            //TODO: заменить код ниже
            id = 24L; 
            //id = Long.parseLong(request.getParameter("id"));            
        } catch (NumberFormatException e) { }
        if (id != null) {
            try {
                SubscriberService service = getServiceFactory()
                        .getSubscriberService();
                Subscriber subscriber = service.getById(id);
                request.setAttribute("subscriber", subscriber);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
