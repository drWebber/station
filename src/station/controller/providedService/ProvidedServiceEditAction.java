package station.controller.providedService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.domain.service.ProvidedService;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.service.ProvidedServicesService;

public class ProvidedServiceEditAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));            
        } catch (NumberFormatException e) { }
        try {
            if (id != null) {
                ProvidedServicesService service =
                        getServiceFactory().getProvidedServicesService();
                ProvidedService providedService = 
                        service.getById(id);
                request.setAttribute("providedService", providedService);

            }
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
