package validator.impl;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import validator.BaseValidator;
import validator.Validator;
import domain.user.Administrator;
import domain.user.Role;
import exception.validator.IncorrectFormDataException;
import exception.validator.ValidatorException;

public class AdministratorValidator extends BaseValidator
        implements Validator<Administrator> {

    public AdministratorValidator(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Administrator validate() throws ValidatorException,
            IncorrectFormDataException {
        Administrator administrator = new Administrator();
        try {
            Long id = getLongParameter("id");
            administrator.getUser().setId(id);
            administrator.setId(id);
        } catch (IncorrectFormDataException e) {
            /* do nothing, because `id` can be null on create action */
        }

        if (administrator.getId() == null) {
            administrator.getUser().setLogin(getStringParameter("login"));
            administrator.getUser().setPassword(
                    getStringParameter("password"));
            try {
                administrator.getUser().cryptPassword();
            } catch (NoSuchAlgorithmException e) {
                logger.error(e);
                throw new ValidatorException(e);
            }
        }
        administrator.getUser().setSurname(getStringParameter("surname"));
        administrator.getUser().setName(getStringParameter("name"));
        administrator.getUser().setPatronymic(
                getStringParameter("patronymic"));
        administrator.getUser().setRole(Role.ADMINISTRATOR);
        administrator.getUser().setActive(
                Boolean.parseBoolean(request.getParameter("isActive")));
        administrator.setPersonalId(getIntegerParameter("personalId"));
        administrator.setPosition(getStringParameter("position"));

        return administrator;
    }
}
