package controller.rate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.interfaces.service.RateService;
import validator.ValidatorFactoryImpl;
import validator.impl.RateValidator;
import controller.Action;
import controller.Forwarder;
import domain.service.Rate;
import exception.FactoryException;
import exception.service.ServiceException;
import exception.validator.IncorrectFormDataException;
import exception.validator.ValidatorException;

public class RateSaveAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(RateSaveAction.class.getName());

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Forwarder forwarder;
        Rate callingRate = null;
        try {
            RateValidator rateValidator =
                    new ValidatorFactoryImpl(request).getRateValidator();
            callingRate = rateValidator.validate();
        } catch (ValidatorException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (IncorrectFormDataException e) {
            logger.warn(e);
            forwarder = new Forwarder("/rate/list.html");
            forwarder.getAttributes().put("err_msg", e.getMessage());
            return forwarder;
        }

        try {
            RateService service =
                    getServiceFactory().getRateService();
            service.save(callingRate);
        } catch (FactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }

        forwarder = new Forwarder("/rate/list.html");
        forwarder.getAttributes().put("succ_msg", "The data was "
                + "successfully saved");

        return forwarder;
    }
}
