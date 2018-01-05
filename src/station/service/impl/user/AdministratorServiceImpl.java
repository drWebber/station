package station.service.impl.user;

import java.util.List;

import station.dao.interfaces.user.AdministratorDao;
import station.dao.interfaces.user.UserDao;
import station.domain.user.Administrator;
import station.domain.user.User;
import station.exception.DaoException;
import station.exception.ServiceException;
import station.service.interfaces.user.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
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
            User user = userDao.readByLoginAndPassword(login, password);
            Administrator administrator = null;
            if (user != null) {
                administrator = administratorDao.read(user.getId());
                administrator.setUser(user);
            } else {
            }
            return administrator;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Administrator> getAll() throws ServiceException {
        try {
            List<Administrator> administrators = administratorDao.readAll();
            for (Administrator administrator : administrators) {
                administrator.setUser(userDao.read(administrator.getId()));
            }
            return administrators;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Administrator administrator) throws ServiceException {
        try {
            if(administrator.getId() != null) {
                userDao.update(administrator.getUser());
                administratorDao.update(administrator);
            } else {
                Long id = userDao.create(administrator.getUser());
                administrator.setId(id);
                administratorDao.create(administrator);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            administratorDao.delete(id);
            userDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
