package exception;

public class PassportIdIsNotUnique extends Exception {
    private static final long serialVersionUID = 1L;

    public PassportIdIsNotUnique() {
        super("Passport id isn't unique");
    }

    public PassportIdIsNotUnique(String message, Throwable cause) {
        super(message, cause);
    }

    public PassportIdIsNotUnique(String message) {
        super(message);
    }

    public PassportIdIsNotUnique(Throwable cause) {
        super(cause);
    }

}
