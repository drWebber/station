package controller.providedService;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forwarder;
import domain.service.ProvidedService;
import exception.FactoryException;
import exception.ServiceException;
import service.interfaces.service.ProvidedServicesService;

public class ProvidedServiceSaveAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) { }
        ProvidedService providedService = new ProvidedService();
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            providedService.setId(id);
        } catch(NumberFormatException e) { }
        providedService.setName(request.getParameter("name"));
        providedService.setDescription(request.getParameter("description"));
        
        try {
            Float monthlyFee = 
                    Float.parseFloat(request.getParameter("monthlyFee"));
            Float subscriptionRate = 
                    Float.parseFloat(request.getParameter("subscriptionRate")); 
            providedService.setMonthlyFee(monthlyFee);
            providedService.setSubscriptionRate(subscriptionRate);
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        
        providedService.setRequired(
                Boolean.parseBoolean(request.getParameter("required")));
        
        try {
            ProvidedServicesService service = getServiceFactory().getProvidedServicesService();
            service.save(providedService);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        
        return new Forwarder("/provided-service/list.html");
    }
}
