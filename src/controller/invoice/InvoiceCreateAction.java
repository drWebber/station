package controller.invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.payment.InvoiceService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.service.ServiceException;

public class InvoiceCreateAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(InvoiceCreateAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            InvoiceService service = getServiceFactory().getInvoiceService();
            if (service.canCreate()) {
                service.createInvoices();
            }
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return new Forwarder("/invoice/control.html");
    }

}
