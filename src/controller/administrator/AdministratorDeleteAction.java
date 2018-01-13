package controller.administrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.AdministratorService;
import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;

public class AdministratorDeleteAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(AdministratorDeleteAction.class.getName());

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
                logger.error(e);
                throw new ServletException(e);
            }
        }
        return new Forwarder("/administrator/list.html");
    }
}
