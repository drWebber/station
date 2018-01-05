package service.interfaces.user;

import java.util.List;

import domain.user.Prefix;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface PrefixService extends CompleteService<Integer, Prefix> {
    List<Prefix> getAll() throws ServiceException;
}
