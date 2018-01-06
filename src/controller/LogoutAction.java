package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new Forwarder("/index.html");
    }
}
