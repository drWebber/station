package controller.administrator;

import java.util.List;

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
import exception.service.ServiceException;

public class AdministratorListAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(AdministratorListAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            AdministratorService service =
                    getServiceFactory().getAdministratorService();
            List<Administrator> administrators = service.getAll();
            request.setAttribute("administrators", administrators);
        } catch (FactoryException | ServiceException e) {
            logger.error("Viewing exception", e);
            throw new ServletException(e);
        }
        return null;
    }

}
