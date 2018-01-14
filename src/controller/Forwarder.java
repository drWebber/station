package controller;

import java.util.HashMap;
import java.util.Map;

public class Forwarder {
    private String url;
    private boolean redirect;
    private Map<String, String> attributes = new HashMap<>();

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
    
    public String getUrlWithAttributes() {
        StringBuilder url = new StringBuilder(this.url);
        if (!attributes.isEmpty()) {
            if (!this.url.contains(".html?")) {
                url.append("?");
            } else {
                url.append("&");
            }
            for(String key : attributes.keySet()) {
                url.append(key + "=" + attributes.get(key) + "&");
            }
            url.setLength(url.length() - 1);
        }
        return url.toString();
    }
    
    public boolean isRedirect() {
        return redirect;
    }
    
    public Map<String, String> getAttributes() {
        return attributes;
    }
    
}
