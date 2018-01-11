package controller.invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.payment.InvoiceService;
import util.user.RetrieveException;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.payment.Invoice;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class InvoiceViewAction extends Action {
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
        } catch (NumberFormatException | RetrieveException |
                FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
