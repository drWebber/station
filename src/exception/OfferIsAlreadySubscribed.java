package exception;

public class OfferIsAlreadySubscribed extends Exception {
    private static final long serialVersionUID = 1L;

    public OfferIsAlreadySubscribed(String message, Throwable cause) {
        super(message, cause);
    }

    public OfferIsAlreadySubscribed(String message) {
        super(message);
    }

    public OfferIsAlreadySubscribed(Throwable cause) {
        super(cause);
    }
}
