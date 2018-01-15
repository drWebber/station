package exception;

public class LoginIsNotUnique extends Exception {
    private static final long serialVersionUID = 1L;

    public LoginIsNotUnique() {
        super("Login is not unique");
    }

    public LoginIsNotUnique(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginIsNotUnique(String message) {
        super(message);
    }

    public LoginIsNotUnique(Throwable cause) {
        super(cause);
    }
}
