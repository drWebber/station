package dao.interfaces.service;

import java.util.List;

import dao.CompleteDao;
import domain.service.CallingRate;
import exception.DaoException;

public interface CallingRateDao extends CompleteDao<Short, CallingRate> {
    List<CallingRate> readAll() throws DaoException;
}
