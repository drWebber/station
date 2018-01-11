package util.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.user.User;

public class UserRetriever<T extends User> {
    HttpServletRequest request;

    public UserRetriever(HttpServletRequest request) {
        this.request = request;
    }
    
    @SuppressWarnings("unchecked")
    public T getCurrentUser() throws RetrieveException {
        HttpSession httpSession = request.getSession(false);
        try {
            T currentUser = null;
            if(httpSession != null){
                currentUser = (T) httpSession.getAttribute("currentUser");
            }
            return currentUser;        
        } catch (ClassCastException e) {
            throw new RetrieveException(e);
        }
    }
}
