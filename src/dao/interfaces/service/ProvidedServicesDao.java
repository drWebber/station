package dao.interfaces.service;

import java.util.List;

import dao.CompleteDao;
import domain.service.ProvidedService;
import exception.DaoException;

public interface ProvidedServicesDao extends CompleteDao<Integer, ProvidedService> {
    List<ProvidedService> readAll() throws DaoException;
    List<ProvidedService> readByRequirement(boolean require) 
            throws DaoException;
}
