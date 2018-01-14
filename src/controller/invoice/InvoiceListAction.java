package controller.invoice;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.payment.InvoiceService;
import service.interfaces.payment.PaymentService;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.payment.Invoice;
import domain.payment.Payment;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.RetrieveException;
import exception.ServiceException;

public class InvoiceListAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(InvoiceListAction.class.getName());
    
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
            
            PaymentService paymentService =
                    getServiceFactory().getPaymentService();
            List<Payment> payments = paymentService.getBySubscriber(subscriber);
            request.setAttribute("payments", payments);
        } catch (RetrieveException | FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        } 
        return null;
    }
}
