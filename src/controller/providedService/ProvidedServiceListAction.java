package controller.providedService;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.ProvidedServicesService;
import controller.Action;
import controller.Forwarder;
import domain.service.ProvidedService;
import exception.FactoryException;
import exception.ServiceException;

public class ProvidedServiceListAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
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
