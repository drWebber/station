package controller.invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.payment.InvoiceService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;

public class InvoiceCreateAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            InvoiceService service = getServiceFactory().getInvoiceService();
            if (service.canCreate()) {
                service.createInvoices();
            }
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/invoice/control.html");
    }

}
