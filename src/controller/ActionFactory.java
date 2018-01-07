package controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;

import controller.administrator.AdministratorDeleteAction;
import controller.administrator.AdministratorEditAction;
import controller.administrator.AdministratorListAction;
import controller.administrator.AdministratorSaveAction;
import controller.administrator.AdministratorViewAction;
import controller.offer.OfferDeleteAction;
import controller.offer.OfferEditAction;
import controller.offer.OfferListAction;
import controller.offer.OfferSaveAction;
import controller.rates.RateEditAction;
import controller.rates.RateListAction;
import controller.rates.RateSaveAction;
import controller.subscriber.SubscriberDeleteAction;
import controller.subscriber.SubscriberEditAction;
import controller.subscriber.SubscriberListAction;
import controller.subscriber.SubscriberSaveAction;
import controller.subscription.SubscriptionListAction;
import controller.subscription.SubscriptionAcceptAction;
import controller.subscription.SubscriptionRejectAction;


public class ActionFactory {
    private static Map<String, Class<? extends Action>> 
            actions = new ConcurrentHashMap<>();
    
    static {
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/subscriber/list", SubscriberListAction.class);
        actions.put("/subscriber/edit", SubscriberEditAction.class);
        actions.put("/subscriber/save", SubscriberSaveAction.class);
        actions.put("/subscriber/delete", SubscriberDeleteAction.class);
        actions.put("/administrator/view", AdministratorViewAction.class);
        actions.put("/administrator/list", AdministratorListAction.class);
        actions.put("/administrator/edit", AdministratorEditAction.class);
        actions.put("/administrator/save", AdministratorSaveAction.class);
        actions.put("/administrator/delete", AdministratorDeleteAction.class);
        actions.put("/offer/list", OfferListAction.class);
        actions.put("/offer/edit", OfferEditAction.class);
        actions.put("/offer/save", OfferSaveAction.class);
        actions.put("/offer/delete", OfferDeleteAction.class);
        actions.put("/subscription/list", SubscriptionListAction.class);
        actions.put("/subscription/accept", SubscriptionAcceptAction.class);
        actions.put("/subscription/reject", SubscriptionRejectAction.class);
        actions.put("/rate/edit", RateEditAction.class);
        actions.put("/rate/list", RateListAction.class);
        actions.put("/rate/save", RateSaveAction.class);
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
