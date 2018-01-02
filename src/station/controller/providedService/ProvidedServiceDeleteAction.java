package station.controller.providedService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.service.ProvidedServicesService;

public class ProvidedServiceDeleteAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException e) { }
        
        if (id != null) {
            try {
                ProvidedServicesService service = 
                        getServiceFactory().getProvidedServicesService();
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forwarder("/provided-service/list.html");
    }
}
