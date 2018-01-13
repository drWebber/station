package controller.rate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.RateService;
import controller.Action;
import controller.Forwarder;
import domain.service.Rate;
import domain.service.RateType;
import exception.FactoryException;
import exception.ServiceException;

public class RateSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(RateSaveAction.class.getName());
    
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
            logger.error(e);
            throw new ServletException(e);
        }
        return new Forwarder("/rate/list.html");
    }
}
