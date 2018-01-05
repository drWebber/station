package station.controller.service;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import station.controller.Action;
import station.controller.Forwarder;
import station.domain.service.Service;
import station.domain.user.Subscriber;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.service.ServicesService;

public class ServiceListAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        //TODO: проверить извлечение из сессии
        HttpSession httpSession = request.getSession(false); 
        Subscriber subscriber = null;
        if(httpSession != null){
            subscriber = (Subscriber) httpSession.getAttribute("currentUser");
        }
        try {
            ServicesService service = getServiceFactory().getServicesService();
            List<Service> services = service.getSubscriberServices(subscriber);
            request.setAttribute("services", services);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
