package station.controller.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.domain.service.ProvidedService;
import station.domain.service.Service;
import station.domain.user.Subscriber;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.service.ServicesService;

public class ServiceSubscribeAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        //TODO: Заменить парсинг по ID на взятие пользователя из сессии!!!
        Long id = null;
        Integer serviceId = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
            serviceId = Integer.parseInt(request.getParameter("serviceId"));
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        Subscriber subscriber = new Subscriber();
        subscriber.setId(id);
        Service subscriberService = new Service();
        subscriberService.setSubscriber(subscriber);
        ProvidedService providedSrv = new ProvidedService();
        providedSrv.setId(serviceId);
        subscriberService.setProvidedService(providedSrv);
        try {
            ServicesService service = getServiceFactory().getServicesService();
            service.save(subscriberService);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/service/list.html");
    }
}
