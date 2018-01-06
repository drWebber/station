package controller.callingRates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.CallingRateService;
import controller.Action;
import controller.Forwarder;
import domain.service.CallingRate;
import exception.FactoryException;
import exception.ServiceException;

public class CallingRateEditAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Short id = null;
        try {
            id = Short.parseShort(request.getParameter("id"));
        } catch (NumberFormatException e) { }
        if (id != null) {
            try {
                CallingRateService service = 
                        getServiceFactory().getCallingRateService();
                CallingRate callingRate = service.getById(id);
                request.setAttribute("callingRate", callingRate);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
