package controller.administrator;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.user.AdministratorService;

public class AdministratorListAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            AdministratorService service = 
                    getServiceFactory().getAdministratorService();
            List<Administrator> administrators = service.getAll();
            request.setAttribute("administrators", administrators);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }

}
