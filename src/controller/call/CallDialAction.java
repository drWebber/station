package controller.call;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.user.PrefixService;
import controller.Action;
import controller.Forwarder;
import domain.user.Prefix;
import exception.FactoryException;
import exception.ServiceException;

public class CallDialAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(CallDialAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            PrefixService prefixService =
                    getServiceFactory().getPrefixService();
            List<Prefix> prefixes = prefixService.getAll();
            request.setAttribute("prefixes", prefixes);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
