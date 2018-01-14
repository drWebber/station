package validator;

import exception.IncorrectFormDataException;
import exception.ValidatorException;

public interface ValidatorFactory {
    AdministratorValidator getAdministratorValidator()
            throws ValidatorException, IncorrectFormDataException;
}
