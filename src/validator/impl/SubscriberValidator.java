package validator.impl;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import util.user.UserRetriever;
import validator.BaseValidator;
import validator.Validator;
import domain.user.Administrator;
import domain.user.Role;
import domain.user.Subscriber;
import exception.IncorrectFormDataException;
import exception.RetrieveException;
import exception.ValidatorException;

public class SubscriberValidator extends BaseValidator
        implements Validator<Subscriber> {

    public SubscriberValidator(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public Subscriber validate()
            throws IncorrectFormDataException, ValidatorException {

        Subscriber subscriber = new Subscriber();
        try {
            Long id = getLongParameter("id");
            subscriber.getUser().setId(id);
            subscriber.setId(id);
        } catch(IncorrectFormDataException e) {
            /* do nothing, because `id` can be null on create action */
        }
            
        if (subscriber.getId() == null) {
            subscriber.getUser().setLogin(getStringParameter("login"));
            subscriber.getUser().setPassword(getStringParameter("password"));
            try {
                subscriber.getUser().cryptPassword();
            } catch (NoSuchAlgorithmException e) {
                logger.error(e);
                throw new ValidatorException(e);
            }
        }
        subscriber.getUser().setSurname(getStringParameter("surname"));
        subscriber.getUser().setName(getStringParameter("name"));
        subscriber.getUser().setPatronymic(getStringParameter("patronymic"));
        subscriber.getUser().setRole(Role.SUBSCRIBER);
        subscriber.getUser().setActive(
                Boolean.parseBoolean(request.getParameter("isActive")));
        subscriber.setPassportId(getStringParameter("passportId"));
        subscriber.setBirthDay(getDateParameter("birthday"));
        subscriber.setAddress(request.getParameter("address"));
        subscriber.getPrefix().setId(getIntegerParameter("prefix"));
        subscriber.setPhoneNum(getIntegerParameter("phoneNum"));
        
        try {
            Administrator administrator = 
                    new UserRetriever<Administrator>(request).getCurrentUser();
            subscriber.setAdministrator(administrator);
        } catch (RetrieveException e) {
            logger.error(e);
            new ValidatorException(e);
        }
        
        return subscriber;
    }
}
