package controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.AdministratorService;
import service.interfaces.user.SubscriberService;
import domain.user.Administrator;
import domain.user.Role;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.service.ServiceException;

public class LoginAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        if (login != null && password != null) {
            if (role.equals(Role.ADMINISTRATOR.toString())) {
                try {
                    AdministratorService service =
                            getServiceFactory().getAdministratorService();
                    Administrator administrator =
                            service.getByLoginAndPassword(login, password);
                    if (authorize(request, administrator)) {
                        return new Forwarder("/subscriber/list.html");
                    }
                } catch (FactoryException | ServiceException e) {
                    logger.error(e);
                    throw new ServletException(e);
                }
            } else if (role.equals(Role.SUBSCRIBER.toString())) {
                try {
                    SubscriberService service =
                            getServiceFactory().getSubscriberService();
                    Subscriber subscriber =
                            service.getByLoginAndPassword(login, password);
                    if (authorize(request, subscriber)) {
                        return new Forwarder("/invoice/list.html");
                    }
                } catch (FactoryException | ServiceException e) {
                    logger.error(e);
                    throw new ServletException(e);
                }
            }
        }

        Set<Role> roles = new HashSet<>();
        roles.addAll(Arrays.asList(Role.values()));
        roles.remove(Role.GUEST);
        request.setAttribute("roles", roles);

        return null;
    }

    private static <T> boolean authorize(HttpServletRequest request,
            T currentUser) {
        boolean result = false;
        if (currentUser != null) {
            result = true;
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);
        } else {
            request.setAttribute("message", "login.authorizationFail");
        }
        return result;
    }
}
