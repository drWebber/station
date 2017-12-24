package station.service.impl.payment;

import station.dao.interfaces.payment.InvoiceDao;
import station.domain.payment.Invoice;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.payment.InvoiceService;

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
            Invoice invoice = invoiceDao.read(id);
            return invoice;
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
