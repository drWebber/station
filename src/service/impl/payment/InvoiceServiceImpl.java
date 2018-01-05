package service.impl.payment;

import dao.interfaces.payment.InvoiceDao;
import domain.payment.Invoice;
import exception.DaoException;
import exception.ServiceException;
import service.interfaces.payment.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceDao invoiceDao;

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }
    
    @Override
    public Invoice getById(Long id) throws ServiceException {
        try {
            return invoiceDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Invoice invoice) throws ServiceException {
        try {
            if(invoice.getId() != null) {
                invoiceDao.update(invoice);
            } else {
                invoiceDao.create(invoice);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            invoiceDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
