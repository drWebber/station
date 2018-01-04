package station.controller.service;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        //TODO: Заменить парсинг по ID на взятие пользователя из сессии!!!
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        Subscriber subscriber = new Subscriber();
        subscriber.setId(id);
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
