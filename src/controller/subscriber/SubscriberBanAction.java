package controller.subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.user.SubscriberService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;

public class SubscriberBanAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            SubscriberService service =
                    getServiceFactory().getSubscriberService();
            service.banById(id);
        } catch (FactoryException | NumberFormatException |
                ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/invoice/control.html");
    }

}
