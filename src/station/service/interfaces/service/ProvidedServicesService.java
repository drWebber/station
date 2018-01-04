package station.service.interfaces.service;

import java.util.List;

import station.domain.service.ProvidedService;
import station.exception.ServiceException;
import station.service.interfaces.CompleteService;

public interface ProvidedServicesService 
        extends CompleteService<Integer, ProvidedService> {
    List<ProvidedService> getAll() throws ServiceException;
    List<ProvidedService> getByRequirement(boolean require) 
            throws ServiceException;
}
