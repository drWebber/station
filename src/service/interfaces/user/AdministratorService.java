package service.interfaces.user;

import java.util.List;

import domain.user.Administrator;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface AdministratorService extends CompleteService<Long, Administrator> {
    List<Administrator> getAll() throws ServiceException;

    Administrator getByLoginAndPassword(String login, String password)
            throws ServiceException;

    boolean isBanned(Long id) throws ServiceException;
}
