package exception.service;


public class UserIsBannedException extends ServiceException {
    private static final long serialVersionUID = 2L;

    public UserIsBannedException() {
        super();
    }

    public UserIsBannedException(String message) {
        super("You are banned." + message);
    }
}
