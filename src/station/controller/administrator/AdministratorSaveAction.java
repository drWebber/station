package station.controller.administrator;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.controller.Action;
import station.controller.Forwarder;
import station.domain.user.Administrator;
import station.domain.user.Role;
import station.domain.user.User;
import station.exception.FactoryException;
import station.exception.ServiceException;
import station.service.interfaces.user.AdministratorService;

public class AdministratorSaveAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
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
        }
        user.setSurname(request.getParameter("surname"));
        user.setName(request.getParameter("name"));
        user.setPatronymic(request.getParameter("patronymic"));
        user.setRole(Role.SUBSCRIBER);
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
