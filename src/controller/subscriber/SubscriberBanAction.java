package controller.subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.SubscriberService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.service.ServiceException;

public class SubscriberBanAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(SubscriberBanAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            SubscriberService service =
                    getServiceFactory().getSubscriberService();
            service.banById(id);
        } catch (FactoryException | NumberFormatException
                | ServiceException e) {
            logger.error("Ban exception", e);
            throw new ServletException(e);
        }
        return new Forwarder("/invoice/control.html");
    }

}
