package exception.service;


public class LoginIsNotUnique extends ServiceException {
    private static final long serialVersionUID = 2L;

    public LoginIsNotUnique(String login) {
        super("Login " + login + " is not unique");
    }
}
