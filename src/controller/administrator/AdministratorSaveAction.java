package controller.administrator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.user.AdministratorService;
import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import domain.user.Role;
import domain.user.User;
import exception.FactoryException;
import exception.ServiceException;

public class AdministratorSaveAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            //TODO: заменить энкодинг на фильтр
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) { }
        Administrator administrator = new Administrator();
        User user = new User();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            user.setId(id);
            administrator.setId(id);
        } catch(NumberFormatException e) { }
        if (administrator.getId() == null) {
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            try {
                user.cryptPassword();
            } catch (NoSuchAlgorithmException e) {
                throw new ServletException(e);
            }
        }
        user.setSurname(request.getParameter("surname"));
        user.setName(request.getParameter("name"));
        user.setPatronymic(request.getParameter("patronymic"));
        user.setRole(Role.ADMINISTRATOR);
        user.setActive(Boolean.parseBoolean(request.getParameter("isActive")));
        administrator.setUser(user);

        try {
            Integer personalId = Integer.parseInt(request.getParameter("personalId"));
            administrator.setPersonalId(personalId);
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        
        administrator.setPosition(request.getParameter("position"));
        
        try {
            AdministratorService service = getServiceFactory().getAdministratorService();
            service.save(administrator);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        
        return new Forwarder("/administrator/list.html");
    }
}
