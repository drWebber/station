package controller.invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.payment.InvoiceService;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.payment.Invoice;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.RetrieveException;
import exception.service.ServiceException;

public class InvoiceViewAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(InvoiceViewAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            Subscriber subscriber =
                    new UserRetriever<Subscriber>(request).getCurrentUser();
            Long id = Long.parseLong(request.getParameter("id"));
            InvoiceService service = getServiceFactory().getInvoiceService();
            Invoice invoice = service.getWithDetails(id, subscriber);
            request.setAttribute("invoice", invoice);
        } catch (RetrieveException | FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            logger.warn(e);
        }
        return null;
    }
}
