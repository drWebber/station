package station.service.impl.user;

import java.util.List;

import station.dao.interfaces.user.PrefixDao;
import station.domain.user.Prefix;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.user.PrefixService;

public class PrefixServiceImpl implements PrefixService {
    private PrefixDao prefixDao;

    public PrefixDao getPrefixDao() {
        return prefixDao;
    }

    public void setPrefixDao(PrefixDao prefixDao) {
        this.prefixDao = prefixDao;
    }

    @Override
    public Prefix getById(Integer id) throws ServiceException {
        try {
            return prefixDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prefix> getAll() throws ServiceException {
        try {
            return prefixDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Prefix prefix) throws ServiceException {
        try {
            if(prefix.getId() != null) {
                prefixDao.update(prefix);
            } else {
                prefixDao.create(prefix);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            prefixDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
