package controller.call;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.CallService;
import validator.ValidatorFactoryImpl;
import validator.impl.CallValidator;
import controller.Action;
import controller.Forwarder;
import domain.service.Call;
import exception.FactoryException;
import exception.service.ServiceException;
import exception.service.UserIsBannedException;
import exception.validator.IncorrectFormDataException;
import exception.validator.ValidatorException;

public class CallMakeAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(CallMakeAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Forwarder forwarder = null;
        Call call = null;
        try {
            CallValidator callValidator =
                    new ValidatorFactoryImpl(request).getCallValidator();
            call = callValidator.validate();
        } catch (ValidatorException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (IncorrectFormDataException e) {
            logger.warn(e);
            forwarder = new Forwarder("/call/dial.html");
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        }

        try {
            CallService callService = getServiceFactory().getCallService();
            callService.save(call);
        } catch (UserIsBannedException e) {
            logger.warn(e);
            forwarder = new Forwarder("/call/dial.html");
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }

        forwarder = new Forwarder("/call/dial.html");
        forwarder.getAttributes().put("succ_msg", "Call ended");
        return forwarder;
    }
}
