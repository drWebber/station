package controller.invoice;

import java.util.List;

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

public class InvoiceListAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            Subscriber subscriber =
                    new UserRetriever<Subscriber>(request).getCurrentUser();
            InvoiceService service = getServiceFactory().getInvoiceService();
            List<Invoice> unpaidInvoices =
                    service.getInvoices(subscriber, false);
            request.setAttribute("unpaidInvoices", unpaidInvoices);
            
            List<Invoice> paidInvoices =
                    service.getInvoices(subscriber, true);
            request.setAttribute("paidInvoices", paidInvoices);
        } catch (RetrieveException | FactoryException | ServiceException e) {
            throw new ServletException(e);
        } 
        return null;
    }
}
