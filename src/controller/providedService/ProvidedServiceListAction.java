package controller.providedService;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import controller.Forwarder;
import domain.service.ProvidedService;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.service.ProvidedServicesService;

public class ProvidedServiceListAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        //TODO: проверить извлечение из сессии
        HttpSession httpSession = request.getSession(false); 
        Subscriber subscriber = null;
        if(httpSession != null){
            subscriber = (Subscriber) httpSession.getAttribute("currentUser");
            request.setAttribute("subscriber", subscriber);
        }
        try {
            ProvidedServicesService service = getServiceFactory()
                    .getProvidedServicesService();
            List<ProvidedService> additionalServices = 
                    service.getByRequirement(false);
            request.setAttribute("additionalServices", additionalServices);
            List<ProvidedService> requiredServices = 
                    service.getByRequirement(true);
            request.setAttribute("requiredServices", requiredServices);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
