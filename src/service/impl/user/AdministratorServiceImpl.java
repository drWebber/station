package service.impl.user;

import java.util.List;

import dao.interfaces.user.AdministratorDao;
import dao.interfaces.user.UserDao;
import domain.user.Administrator;
import domain.user.User;
import exception.DaoException;
import exception.ServiceException;
import exception.TransactionException;
import service.impl.TransactionService;
import service.interfaces.user.AdministratorService;

public class AdministratorServiceImpl extends TransactionService
        implements AdministratorService {
    private UserDao userDao;
    private AdministratorDao administratorDao;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAdministratorDao(AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

    @Override
    public Administrator getById(Long id) throws ServiceException {
        try {
            Administrator administrator = administratorDao.read(id);
            if (administrator != null) {
                administrator.setUser(userDao.read(id));
            }
            return administrator;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Administrator getByLoginAndPassword(String login, String password) 
            throws ServiceException {
        try {
            getTransaction().start();
            User user = userDao.readByLoginAndPassword(login, password);
            Administrator administrator = null;
            if (user != null) {
                administrator = administratorDao.read(user.getId());
                if (administrator != null) {
                    administrator.setUser(user);
                }
            }
            getTransaction().commit();
            return administrator;
        } catch(DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Administrator> getAll() throws ServiceException {
        try {
            getTransaction().start();
            List<Administrator> administrators = administratorDao.readAll();
            for (Administrator administrator : administrators) {
                administrator.setUser(userDao.read(administrator.getId()));
            }
            getTransaction().commit();
            return administrators;
        } catch(DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Administrator administrator) throws ServiceException {
        try {
            getTransaction().start();
            if(administrator.getId() != null) {
                userDao.update(administrator.getUser());
                administratorDao.update(administrator);
            } else {
                Long id = userDao.create(administrator.getUser());
                administrator.setId(id);
                administratorDao.create(administrator);
            }
            getTransaction().commit();
        } catch(DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            getTransaction().start();
            administratorDao.delete(id);
            userDao.delete(id);
            getTransaction().commit();
        } catch(DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) { }
            throw new ServiceException(e);
        }
    }
}
