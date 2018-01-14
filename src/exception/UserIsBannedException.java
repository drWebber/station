package exception;

public class UserIsBannedException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserIsBannedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsBannedException(String message) {
        super(message);
    }

    public UserIsBannedException(Throwable cause) {
        super(cause);
    }

}
