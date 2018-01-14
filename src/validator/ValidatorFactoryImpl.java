package validator;

import javax.servlet.http.HttpServletRequest;

import exception.IncorrectFormDataException;
import exception.ValidatorException;
import validator.impl.AdministratorValidator;
import validator.impl.CallValidator;

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
}
