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
    public T getCurrentUser() throws ClassCastException {
        //TODO: уточнить по возможности применения метода
        HttpSession httpSession = request.getSession(false); 
        T currentUser = null;
        if(httpSession != null){
            currentUser = (T) httpSession.getAttribute("currentUser");
        }
        return currentUser;
    }
}
