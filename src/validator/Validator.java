package validator;

import domain.Entity;
import exception.IncorrectFormDataException;
import exception.ValidatorException;

public interface Validator<T extends Entity<?>> {
    T validate() throws ValidatorException, IncorrectFormDataException;
}
