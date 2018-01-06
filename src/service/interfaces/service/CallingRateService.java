package service.interfaces.service;

import java.util.List;

import domain.service.CallingRate;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface CallingRateService extends CompleteService<Short, CallingRate> {
    List<CallingRate> getAll() throws ServiceException;
}
