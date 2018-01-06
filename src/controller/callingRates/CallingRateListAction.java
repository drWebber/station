package controller.callingRates;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.CallingRateService;
import controller.Action;
import controller.Forwarder;
import domain.service.CallingRate;
import exception.FactoryException;
import exception.ServiceException;

public class CallingRateListAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        CallingRateService service;
        try {
            service = getServiceFactory().getCallingRateService();
            List<CallingRate> rates = service.getAll();
            request.setAttribute("rates", rates);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
