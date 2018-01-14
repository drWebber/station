package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.administrator.AdministratorSaveAction;
import exception.IncorrectFormDataException;

public class BaseValidator {
    protected Logger logger =
            LogManager.getLogger(AdministratorSaveAction.class.getName());
    protected HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public BaseValidator(HttpServletRequest request) {
        this.setRequest(request);
    }

    public Logger getLogger() {
        return logger;
    }

    protected String getStringParameter(String parameter)
            throws IncorrectFormDataException {
        String value = request.getParameter(parameter);
        if (value.isEmpty()) {
            throw new IncorrectFormDataException(parameter, value);
        }
        return value;
    }

    protected Long getLongParameter(String parameter) 
            throws IncorrectFormDataException {
        try {
            return Long.parseLong(request.getParameter(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException(parameter, "");
        }
    }

    protected Integer getIntegerParameter(String parameter)
            throws IncorrectFormDataException {
        try {
            return Integer.parseInt(request.getParameter(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException(parameter, "");
        }
    }

    protected Float getFloatParameter(String parameter)
            throws IncorrectFormDataException {
        try {
            return Float.parseFloat(request.getParameter(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException(parameter, "");
        }
    }

    protected Date getDateParameter(String parameter)
            throws IncorrectFormDataException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String value = request.getParameter(parameter);
        try {
            java.util.Date date = format.parse(value);
            return new Date(date.getTime());
        } catch (ParseException e) {
            logger.warn(e);
            throw new IncorrectFormDataException(parameter, value);
        }
    }
}
