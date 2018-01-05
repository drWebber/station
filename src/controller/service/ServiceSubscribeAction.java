package controller.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import domain.service.ProvidedService;
import domain.service.Service;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.service.ServicesService;

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
