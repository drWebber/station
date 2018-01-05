package controller;

public class Forwarder {
    private String url;
    private boolean redirect;
    
    public Forwarder(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }
    
    public Forwarder(String url) {
        this(url, true);
    }

    public String getUrl() {
        return url;
    }
    public boolean isRedirect() {
        return redirect;
    }
}
