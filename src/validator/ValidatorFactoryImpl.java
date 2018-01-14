package validator;

import javax.servlet.http.HttpServletRequest;

import exception.IncorrectFormDataException;
import exception.ValidatorException;
import validator.impl.AdministratorValidator;
import validator.impl.CallValidator;
import validator.impl.OfferValidator;
import validator.impl.RateValidator;

public class ValidatorFactoryImpl implements ValidatorFactory {
    private HttpServletRequest request;
    
    public ValidatorFactoryImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public AdministratorValidator getAdministratorValidator() {
        return new AdministratorValidator(request);
    }

    @Override
    public CallValidator getCallValidator() throws ValidatorException,
            IncorrectFormDataException {
        return new CallValidator(request);
    }

    @Override
    public OfferValidator getOfferValidator() throws ValidatorException,
            IncorrectFormDataException {
        return new OfferValidator(request);
    }

    @Override
    public RateValidator getRateValidator() throws ValidatorException,
            IncorrectFormDataException {
        return new RateValidator(request);
    }
}
