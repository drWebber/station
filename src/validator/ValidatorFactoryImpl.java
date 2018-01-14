package validator;

import javax.servlet.http.HttpServletRequest;

public class ValidatorFactoryImpl implements ValidatorFactory {
    private HttpServletRequest request;
    
    public ValidatorFactoryImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public AdministratorValidator getAdministratorValidator() {
        return new AdministratorValidator(request);
    }
}
