package dao.interfaces.service;

import java.util.List;

import dao.BaseDao;
import domain.service.Rate;
import exception.DaoException;

public interface RateDao extends BaseDao<Long, Rate> {
    List<Rate> readCurrentRates() throws DaoException;
}
