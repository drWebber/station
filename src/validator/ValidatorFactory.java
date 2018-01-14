package validator;

import validator.impl.AdministratorValidator;
import validator.impl.CallValidator;
import validator.impl.OfferValidator;
import validator.impl.RateValidator;
import exception.IncorrectFormDataException;
import exception.ValidatorException;

public interface ValidatorFactory {
    AdministratorValidator getAdministratorValidator()
            throws ValidatorException, IncorrectFormDataException;
    
    CallValidator getCallValidator() 
            throws ValidatorException, IncorrectFormDataException;
    
    OfferValidator getOfferValidator() 
            throws ValidatorException, IncorrectFormDataException;
    
    RateValidator getRateValidator()
            throws ValidatorException, IncorrectFormDataException;
}
