package controller.payment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.payment.PaymentService;
import controller.Action;
import controller.Forwarder;
import domain.payment.Invoice;
import domain.payment.Payment;
import exception.FactoryException;
import exception.ServiceException;

public class PaymentPayAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            PaymentService service = getServiceFactory().getPaymentService();
            Invoice invoice = new Invoice();
            invoice.setId(id);
            Payment payment = new Payment();
            payment.setInvoice(invoice);
            service.save(payment);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/invoice/list.html");
    }
}
