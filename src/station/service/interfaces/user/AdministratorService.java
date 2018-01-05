package station.service.interfaces.user;

import java.util.List;

import station.domain.user.Administrator;
import station.exception.ServiceException;
import station.service.interfaces.CompleteService;

public interface AdministratorService extends CompleteService<Long, Administrator> {
    List<Administrator> getAll() throws ServiceException;
    Administrator getByLoginAndPassword(String login, String password) 
            throws ServiceException;
}
