package controller.rate;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.RateService;
import controller.Action;
import controller.Forwarder;
import domain.service.Rate;
import exception.FactoryException;
import exception.ServiceException;

public class RateListAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(RateListAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        RateService service;
        try {
            service = getServiceFactory().getRateService();
            List<Rate> rates = service.getCurrentRates();
            request.setAttribute("rates", rates);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
