package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceFactory;

public abstract class Action {
    private ServiceFactory serviceFactory;

    public ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
    
    public abstract Forwarder execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException;
}
