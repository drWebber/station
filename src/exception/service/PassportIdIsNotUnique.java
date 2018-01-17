package exception.service;

public class PassportIdIsNotUnique extends ServiceException {
    private static final long serialVersionUID = 2L;

    public PassportIdIsNotUnique(String passportId) {
        super("Passport id " + passportId + " isn't unique");
    }
}
