package station.exception;

public class DaoException extends Exception {
    private static final long serialVersionUID = 7459305441523532463L;

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
