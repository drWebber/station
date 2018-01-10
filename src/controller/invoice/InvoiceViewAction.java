package controller.invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.payment.InvoiceService;
import controller.Action;
import controller.Forwarder;
import domain.payment.Invoice;
import exception.FactoryException;
import exception.ServiceException;

public class InvoiceViewAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            InvoiceService service = getServiceFactory().getInvoiceService();
            Invoice invoice = service.getById(id);
            request.setAttribute("invoice", invoice);
        } catch (NumberFormatException | FactoryException |
                ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
