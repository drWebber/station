package exception.service;

public class PhoneIsNotUnique extends ServiceException {
    private static final long serialVersionUID = 2L;

    public PhoneIsNotUnique(Integer phoneNum) {
        super("Phone number " + phoneNum + " isn't unique");
    }
}
