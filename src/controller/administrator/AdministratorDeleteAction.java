package controller.administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.user.AdministratorService;

public class AdministratorDeleteAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch(NumberFormatException e) { }
        
        if (id != null) {
            try {
                AdministratorService service =
                        getServiceFactory().getAdministratorService();
                service.delete(id);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forwarder("/administrator/list.html");
    }
}
