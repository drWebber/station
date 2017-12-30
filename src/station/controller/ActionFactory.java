package station.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;

import station.controller.subscriber.SubscriberEditAction;
import station.controller.subscriber.SubscriberListAction;
import station.controller.subscriber.SubscriberSaveAction;


public class ActionFactory {
    private static Map<String, Class<? extends Action>> 
            actions = new ConcurrentHashMap<>();
    
    static {
        actions.put("/subscriber/edit", SubscriberEditAction.class);
        actions.put("/subscriber/list", SubscriberListAction.class);
        actions.put("/subscriber/save", SubscriberSaveAction.class);
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
