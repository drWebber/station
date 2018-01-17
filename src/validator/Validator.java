package validator;

import domain.Entity;
import exception.validator.IncorrectFormDataException;
import exception.validator.ValidatorException;

public interface Validator<T extends Entity<?>> {
    T validate() throws IncorrectFormDataException, ValidatorException;
}
