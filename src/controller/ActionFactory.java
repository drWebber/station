package controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;

import controller.administrator.AdministratorDeleteAction;
import controller.administrator.AdministratorEditAction;
import controller.administrator.AdministratorListAction;
import controller.administrator.AdministratorSaveAction;
import controller.administrator.AdministratorViewAction;
import controller.providedService.ProvidedServiceDeleteAction;
import controller.providedService.ProvidedServiceEditAction;
import controller.providedService.ProvidedServiceListAction;
import controller.providedService.ProvidedServiceSaveAction;
import controller.service.ServiceListAction;
import controller.service.ServiceSubscribeAction;
import controller.subscriber.SubscriberDeleteAction;
import controller.subscriber.SubscriberEditAction;
import controller.subscriber.SubscriberListAction;
import controller.subscriber.SubscriberSaveAction;


public class ActionFactory {
    private static Map<String, Class<? extends Action>> 
            actions = new ConcurrentHashMap<>();
    
    static {
        actions.put("/login", LoginAction.class);
        actions.put("/subscriber/list", SubscriberListAction.class);
        actions.put("/subscriber/edit", SubscriberEditAction.class);
        actions.put("/subscriber/save", SubscriberSaveAction.class);
        actions.put("/subscriber/delete", SubscriberDeleteAction.class);
        actions.put("/administrator/view", AdministratorViewAction.class);
        actions.put("/administrator/list", AdministratorListAction.class);
        actions.put("/administrator/edit", AdministratorEditAction.class);
        actions.put("/administrator/save", AdministratorSaveAction.class);
        actions.put("/administrator/delete", AdministratorDeleteAction.class);
        actions.put("/provided-service/list", ProvidedServiceListAction.class);
        actions.put("/provided-service/edit", ProvidedServiceEditAction.class);
        actions.put("/provided-service/save", ProvidedServiceSaveAction.class);
        actions.put("/provided-service/delete", 
                ProvidedServiceDeleteAction.class);
        actions.put("/service/list", ServiceListAction.class);
        actions.put("/service/subscribe", ServiceSubscribeAction.class);
    }
    
    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        try {
            return (Action) action.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
        }
    }
}
