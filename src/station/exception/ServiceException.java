package station.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 8314808102493130018L;
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
