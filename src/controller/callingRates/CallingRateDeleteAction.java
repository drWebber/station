package controller.callingRates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.CallingRateService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;

public class CallingRateDeleteAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Short id = null;
        try {
            id = Short.parseShort(request.getParameter("id"));
            CallingRateService service =
                    getServiceFactory().getCallingRateService();
            service.delete(id);
        } catch (NumberFormatException | FactoryException |
                ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/calling-rate/list.html");
    }

}
