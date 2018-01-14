package controller.administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.AdministratorService;
import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import exception.FactoryException;
import exception.ServiceException;

public class AdministratorEditAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(AdministratorEditAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) { }
        try {
            if (id != null) {
                AdministratorService administratorService =
                        getServiceFactory().getAdministratorService();
                Administrator administrator =
                        administratorService.getById(id);
                request.setAttribute("administrator", administrator);
            }
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
