package validator;

import validator.impl.AdministratorValidator;
import validator.impl.CallValidator;
import exception.IncorrectFormDataException;
import exception.ValidatorException;

public interface ValidatorFactory {
    AdministratorValidator getAdministratorValidator()
            throws ValidatorException, IncorrectFormDataException;
    
    CallValidator getCallValidator() throws ValidatorException, 
            IncorrectFormDataException;
}
