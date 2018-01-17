package validator;

import javax.servlet.http.HttpServletRequest;

import exception.validator.IncorrectFormDataException;
import exception.validator.ValidatorException;
import validator.impl.AdministratorValidator;
import validator.impl.CallValidator;
import validator.impl.OfferValidator;
import validator.impl.RateValidator;
import validator.impl.SubscriberValidator;

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

    @Override
    public SubscriberValidator getSubscriberValidator()
            throws ValidatorException, IncorrectFormDataException {
        return new SubscriberValidator(request);
    }
}
