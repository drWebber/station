package controller.administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.user.AdministratorService;

public class AdministratorEditAction extends Action {
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
            throw new ServletException(e);
        }
        return null;
    }
}
