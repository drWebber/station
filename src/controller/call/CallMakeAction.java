package controller.call;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.service.CallService;
import service.interfaces.service.RateService;
import util.service.CallDirection;
import util.service.CallRateResolver;
import util.user.UserRetriever;
import controller.Action;
import controller.Forwarder;
import domain.service.Call;
import domain.service.Rate;
import domain.service.RateType;
import domain.user.Prefix;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class CallMakeAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Integer prefixId = null;
        Integer phoneNum = null;
        Long duration = null;
        try {
            /* Текущий абонент, авторизованный в приложении, 
             * принимающий|осуществляющий вызов 
             */
            Subscriber subscriber = 
                    new UserRetriever<Subscriber>(request).getCurrentUser();
            
            /* Префикс и тел. номер оппонента, принимающего или  
             * осуществляющего вызов 
             */
            prefixId = Integer.parseInt(request.getParameter("prefix"));
            Prefix prefix = new Prefix();
            prefix.setId(prefixId);
            phoneNum = Integer.parseInt(request.getParameter("phoneNum"));
            Subscriber opponent = new Subscriber();
            opponent.setPrefix(prefix);
            
            /* Направление звонка исходящий/входящий */
            CallDirection direction = 
                    CallDirection.valueOf(request.getParameter("direction"));
            
            /* По префиксу и номеру получаем тип тарифа на звонки 
             * RateType (Местные | Межгород | Мобильных операторов)
             */
            RateType rateType = 
                    new CallRateResolver(subscriber, opponent, direction)
                    .getResolvedRate();
            RateService rateService = getServiceFactory().getRateService();
            Rate rate = rateService.getCurrentByType(rateType);
            
            /* Продолжительность звонка */
            duration = Long.parseLong(request.getParameter("duration"));
            
            Call call = new Call();
            call.setSubscriber(subscriber);
            call.setPrefix(prefix);
            call.setPhoneNum(phoneNum);
            long time = System.currentTimeMillis();
            call.setBeginTime(new Timestamp(time));
            call.setFinishTime(new Timestamp(time + duration*1000));
            call.setRate(rate);
            
            CallService callService = getServiceFactory().getCallService();
            callService.save(call);
        } catch (ClassCastException | NumberFormatException | 
                FactoryException | ServiceException | NullPointerException e) {
            throw new ServletException(e);
        }
        return new Forwarder("/call/dial.html");
    }
}
