package controller.invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.payment.InvoiceService;
import service.interfaces.payment.PaymentService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;

public class InvoiceDeleteAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            PaymentService paymentService =
                    getServiceFactory().getPaymentService();
            paymentService.deleteAll();
            
            InvoiceService invoiceService =
                    getServiceFactory().getInvoiceService();
            invoiceService.deleteAll();
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/invoice/control.html");
    }
}
