package service.interfaces.user;

import java.util.List;

import domain.user.Administrator;
import exception.LoginIsNotUnique;
import exception.ServiceException;

public interface AdministratorService {
    Administrator getById(Long id) throws ServiceException;
    
    void save(Administrator administrator)
            throws LoginIsNotUnique, ServiceException;
    
    void delete(Long id) throws ServiceException;
    
    List<Administrator> getAll() throws ServiceException;

    Administrator getByLoginAndPassword(String login, String password)
            throws ServiceException;

    boolean isBanned(Long id) throws ServiceException;
}
