package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.user.Administrator;
import domain.user.Role;
import domain.user.Subscriber;
import exception.DaoException;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.user.AdministratorService;
import service.interfaces.user.SubscriberService;

public class LoginAction extends Action {

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
                        return new Forwarder("/subscriber/list.html"); //TODO :перенаправить на нужную страницу
                    }
                } catch (FactoryException | ServiceException e) {
                    throw new ServletException(e);
                }
            } else if(role.equals(Role.SUBSCRIBER.toString())) {
                try {
                    SubscriberService service = 
                            getServiceFactory().getSubscriberService();
                    Subscriber subscriber =
                            service.getByLoginAndPassword(login, password);
                    if (authorize(request, subscriber)) {
                        return new Forwarder("/service/list.html"); //TODO :перенаправить на нужную страницу
                    }
                } catch (FactoryException | DaoException e) {
                    throw new ServletException(e);
                }
            }
        }
        return null;
    }
    
    private static <T> boolean authorize(HttpServletRequest request, 
            T currentUser) {
        boolean result = false;
        if (currentUser != null) {
            result = true;
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser); /* или завести отдельный аттрибут */
        } else {
            request.setAttribute("message", "Авторизоваться не удалось");
        }
        return result;
    }

}
