package controller.payment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.payment.PaymentService;
import controller.Action;
import controller.Forwarder;
import domain.payment.Payment;
import exception.FactoryException;
import exception.service.InvoiceIsAlreadyBeenPaid;
import exception.service.ServiceException;

public class PaymentPayAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(PaymentPayAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Forwarder forwarder;
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            PaymentService service = getServiceFactory().getPaymentService();
            Payment payment = new Payment();
            payment.getInvoice().setId(id);
            service.save(payment);
        } catch (NumberFormatException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (InvoiceIsAlreadyBeenPaid e) {
            logger.error(e);
            forwarder = new Forwarder("/invoice/list.html");
            forwarder.getAttributes().put("err_msg", 
                    "The invoice has already been paid");
            return forwarder;
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }

        forwarder = new Forwarder("/invoice/list.html");
        forwarder.getAttributes().put("succ_msg", "The invoice has been paid");
        return forwarder;
    }
}
