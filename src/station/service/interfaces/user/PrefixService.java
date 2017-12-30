package station.service.interfaces.user;

import java.util.List;

import station.domain.user.Prefix;
import station.exception.ServiceException;
import station.service.interfaces.CompleteService;

public interface PrefixService extends CompleteService<Integer, Prefix> {
    List<Prefix> getAll() throws ServiceException;
}
