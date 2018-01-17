package exception.service;

public class OfferIsAlreadySubscribed extends ServiceException {
    private static final long serialVersionUID = 2L;
    
    public OfferIsAlreadySubscribed() {
        super("You are already subscribed for this offer");
    }
}
