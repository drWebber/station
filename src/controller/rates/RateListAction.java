package controller.rates;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.RateService;
import controller.Action;
import controller.Forwarder;
import domain.service.Rate;
import exception.FactoryException;
import exception.ServiceException;

public class RateListAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        RateService service;
        try {
            service = getServiceFactory().getRateService();
            List<Rate> rates = service.getCurrentRates();
            request.setAttribute("rates", rates);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
