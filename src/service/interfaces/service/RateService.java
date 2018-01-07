package service.interfaces.service;

import java.util.List;

import service.interfaces.BaseService;
import domain.service.Rate;
import exception.ServiceException;

public interface RateService extends BaseService<Long, Rate> {
    List<Rate> getCurrentRates() throws ServiceException;
}
