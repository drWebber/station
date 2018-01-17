package exception.service;

public class InvoiceIsAlreadyBeenPaid extends ServiceException {
    private static final long serialVersionUID = 1L;
    
    public InvoiceIsAlreadyBeenPaid(Long invoiceId) {
        super("The invoice #" + invoiceId + " is already been paid");
    }
}
