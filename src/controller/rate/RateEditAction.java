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
import exception.FactoryException;
import exception.service.ServiceException;

public class RateEditAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(RateEditAction.class.getName());

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
                logger.error(e);
                throw new ServletException(e);
            }
        }
        return null;
    }
}
