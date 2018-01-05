package service.interfaces.service;

import java.util.List;

import domain.service.ProvidedService;
import exception.ServiceException;
import service.interfaces.CompleteService;

public interface ProvidedServicesService 
        extends CompleteService<Integer, ProvidedService> {
    List<ProvidedService> getAll() throws ServiceException;
    List<ProvidedService> getByRequirement(boolean require) 
            throws ServiceException;
}
