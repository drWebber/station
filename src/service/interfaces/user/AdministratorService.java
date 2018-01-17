package service.interfaces.user;

import java.util.List;

import service.interfaces.CompleteService;
import domain.user.Administrator;
import exception.service.ServiceException;

public interface AdministratorService
        extends CompleteService<Long, Administrator> {    
    List<Administrator> getAll() throws ServiceException;

    Administrator getByLoginAndPassword(String login, String password)
            throws ServiceException;

    boolean isBanned(Long id) throws ServiceException;
    
    boolean isDeletable(Long id) throws ServiceException;
}
