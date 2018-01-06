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

public class CallingRateSaveAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Short id = null;
        try {
            id = Short.parseShort(request.getParameter("id"));
        } catch (NumberFormatException e) { }
        
        Float rate = null;
        try {
            rate = Float.parseFloat(request.getParameter("rate"));
            CallingRate callingRate = new CallingRate();
            callingRate.setId(id);
            callingRate.setName(request.getParameter("name"));
            callingRate.setRate(rate);
            CallingRateService service = 
                    getServiceFactory().getCallingRateService();
            service.save(callingRate);
        } catch (NumberFormatException | FactoryException |
                ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/calling-rate/list.html");
    }
}
