package exception;

public class PhoneIsNotUnique extends Exception {
    private static final long serialVersionUID = 1L;

    public PhoneIsNotUnique() {
        super("Phone number isn't unique");
    }

    public PhoneIsNotUnique(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneIsNotUnique(String message) {
        super(message);
    }

    public PhoneIsNotUnique(Throwable cause) {
        super(cause);
    }

}
