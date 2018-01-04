package station.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;

import station.controller.administrator.AdministratorDeleteAction;
import station.controller.administrator.AdministratorEditAction;
import station.controller.administrator.AdministratorListAction;
import station.controller.administrator.AdministratorSaveAction;
import station.controller.administrator.AdministratorViewAction;
import station.controller.providedService.ProvidedServiceDeleteAction;
import station.controller.providedService.ProvidedServiceEditAction;
import station.controller.providedService.ProvidedServiceListAction;
import station.controller.providedService.ProvidedServiceSaveAction;
import station.controller.service.ServiceListAction;
import station.controller.service.ServiceSubscribeAction;
import station.controller.subscriber.SubscriberDeleteAction;
import station.controller.subscriber.SubscriberEditAction;
import station.controller.subscriber.SubscriberListAction;
import station.controller.subscriber.SubscriberSaveAction;


public class ActionFactory {
    private static Map<String, Class<? extends Action>> 
            actions = new ConcurrentHashMap<>();
    
    static {
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
