package controller.rates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.RateService;
import controller.Action;
import controller.Forwarder;
import domain.service.Rate;
import exception.FactoryException;
import exception.ServiceException;

public class RateEditAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) { }
        if (id != null) {
            try {
                RateService service = 
                        getServiceFactory().getRateService();
                Rate rate = service.getById(id);
                request.setAttribute("rate", rate);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}