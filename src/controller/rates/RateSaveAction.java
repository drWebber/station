package controller.rates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.RateService;
import controller.Action;
import controller.Forwarder;
import domain.service.Rate;
import domain.service.RateType;
import exception.FactoryException;
import exception.ServiceException;

public class RateSaveAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Float tariff = null;
        try {
            tariff = Float.parseFloat(request.getParameter("tariff"));
            Rate callingRate = new Rate();
            callingRate.setType(RateType.valueOf(request.getParameter("type")));
            callingRate.setTariff(tariff);
            RateService service = 
                    getServiceFactory().getRateService();
            service.save(callingRate);
        } catch (NumberFormatException | FactoryException |
                ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/rate/list.html");
    }
}
