package station.dao.interfaces.service;

import java.util.List;

import station.dao.CompleteDao;
import station.domain.service.ProvidedService;
import station.exception.DaoException;

public interface ProvidedServicesDao extends CompleteDao<Integer, ProvidedService> {
    List<ProvidedService> readAll() throws DaoException;
    List<ProvidedService> readByRequirement(boolean require) 
            throws DaoException;
}
