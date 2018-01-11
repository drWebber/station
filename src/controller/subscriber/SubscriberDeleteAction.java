package controller.subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.user.SubscriberService;

public class SubscriberDeleteAction extends Action {

    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
            SubscriberService service =
                    getServiceFactory().getSubscriberService();
            service.delete(id);
        } catch(NumberFormatException e) {
            //TODO: log4j неверный формат
            throw new ServletException(e);
        } catch (FactoryException e) {
            //TODO: log4j ошибка фабрики
            throw new ServletException(e);
        } catch (ServiceException e) {
            //TODO: log4j ошибка сервиса
            throw new ServletException(e);
        }
        return new Forwarder("/subscriber/list.html");
    }
}
