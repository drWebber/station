package exception.validator;

public class IncorrectFormDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public IncorrectFormDataException(String param, String value) {
        super(String.format("Empty or incorrect \"%s\" parameter was found%s",
                param, value.isEmpty() ? "" : ": " + value));
    }

    public IncorrectFormDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFormDataException(String message) {
        super(message);
    }

    public IncorrectFormDataException(Throwable cause) {
        super(cause);
    }
}
