package controller.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.ServicesService;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.service.Service;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class ServiceUnsubscribeAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch(NumberFormatException e) {
            throw new ServletException(e);
        }
        
        if (id != null) {
            //TODO: проверить извлечение из сессии
            Subscriber subscriber = null;
            try {
                subscriber = 
                        new UserRetriever<Subscriber>(request).getCurrentUser();
            } catch (ClassCastException e) {
                throw new ServletException(e);
            }
            try {
                ServicesService service = 
                        getServiceFactory().getServicesService();
                Service subscriberService = new Service();
                subscriberService.setId(id);
                subscriberService.setSubscriber(subscriber);
                service.save(subscriberService);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forwarder("/service/list.html");
    }
}
