package exception;

public class FactoryException extends Exception {
    private static final long serialVersionUID = 1L;

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(Throwable cause) {
        super(cause);
    }
}
