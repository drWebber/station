package controller.service;

import java.util.List;

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

public class ServiceListAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            //TODO: проверить извлечение из сессии
            Subscriber subscriber = 
                    new UserRetriever<Subscriber>(request).getCurrentUser();
            ServicesService service = getServiceFactory().getServicesService();
            List<Service> services = service.getSubscriberServices(subscriber);
            request.setAttribute("services", services);
        } catch (FactoryException | ServiceException | ClassCastException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
