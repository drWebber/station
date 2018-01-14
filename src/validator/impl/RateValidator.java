package validator.impl;

import javax.servlet.http.HttpServletRequest;

import domain.service.Rate;
import domain.service.RateType;
import exception.IncorrectFormDataException;
import exception.ValidatorException;
import validator.BaseValidator;
import validator.Validator;

public class RateValidator extends BaseValidator implements Validator<Rate> {

    public RateValidator(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Rate validate()
            throws IncorrectFormDataException, ValidatorException {
        Rate callingRate = new Rate();
        Float tariff = getFloatParameter("tariff");
        callingRate.setTariff(tariff);
        String rateType = getStringParameter("type");
        callingRate.setType(RateType.valueOf(rateType));
        return callingRate;
    }
}
