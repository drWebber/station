package controller.administrator;

import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.AdministratorService;
import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import domain.user.Role;
import exception.FactoryException;
import exception.ServiceException;

public class AdministratorSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(AdministratorSaveAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Administrator administrator = new Administrator();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            administrator.getUser().setId(id);
            administrator.setId(id);
        } catch(NumberFormatException e) { }
        if (administrator.getId() == null) {
            administrator.getUser().setLogin(request.getParameter("login"));
            administrator.getUser().setPassword(
                    request.getParameter("password"));
            try {
                administrator.getUser().cryptPassword();
            } catch (NoSuchAlgorithmException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        }
        administrator.getUser().setSurname(request.getParameter("surname"));
        administrator.getUser().setName(request.getParameter("name"));
        administrator.getUser().setPatronymic(request.getParameter("patronymic"));
        administrator.getUser().setRole(Role.ADMINISTRATOR);
        administrator.getUser().setActive(
                Boolean.parseBoolean(request.getParameter("isActive")));
        try {
            Integer personalId = Integer.parseInt(
                    request.getParameter("personalId"));
            administrator.setPersonalId(personalId);
        } catch (NumberFormatException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        administrator.setPosition(request.getParameter("position"));
        
        try {
            AdministratorService service =
                    getServiceFactory().getAdministratorService();
            service.save(administrator);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        
        return new Forwarder("/administrator/list.html");
    }
}
