package controller.invoice;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.payment.InvoiceService;
import service.interfaces.user.SubscriberService;
import controller.Action;
import controller.Forwarder;
import domain.payment.Invoice;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.service.ServiceException;

public class InvoiceControlAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(InvoiceControlAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            InvoiceService invoiceService =
                    getServiceFactory().getInvoiceService();
            boolean canCreate = invoiceService.canCreate();
            request.setAttribute("canCreate", canCreate);

            SubscriberService subscriberService =
                    getServiceFactory().getSubscriberService();
            List<Invoice> unpaid = invoiceService.getUnpaid();
            for (Invoice invoice : unpaid) {
                Long id = invoice.getSubscriber().getId();
                Subscriber sub = subscriberService.getById(id);
                invoice.setSubscriber(sub);
            }
            request.setAttribute("unpaid", unpaid);
        } catch (FactoryException | ServiceException e) {
            logger.error("InvoiceControl exception", e);
            throw new ServletException(e);
        }
        return null;
    }
}
