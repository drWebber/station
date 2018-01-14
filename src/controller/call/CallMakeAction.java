package controller.call;

import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import domain.user.Subscriber;
import exception.FactoryException;
import exception.RetrieveException;
import exception.ServiceException;

public class CallMakeAction extends Action {
    private static Logger logger = 
            LogManager.getLogger(CallMakeAction.class.getName());
    
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        Integer prefixId = null;
        Integer phoneNum = null;
        Long duration = null;
        try {
            /* current Subscriber */
            Subscriber subscriber = 
                    new UserRetriever<Subscriber>(request).getCurrentUser();
            
             /* Opponent - opposite wire end subscriber */
            prefixId = Integer.parseInt(request.getParameter("prefix"));
            phoneNum = Integer.parseInt(request.getParameter("phoneNum"));
            Subscriber opponent = new Subscriber();
            opponent.getPrefix().setId(prefixId);
            
            /* Call direction INCOMING|OUTGOING */
            CallDirection direction = 
                    CallDirection.valueOf(request.getParameter("direction"));
            
            /* Call rate type determining */
            RateType rateType = 
                    new CallRateResolver(subscriber, opponent, direction)
                    .getResolvedRate();
            RateService rateService = getServiceFactory().getRateService();
            Rate rate = rateService.getCurrentByType(rateType);
            
            duration = Long.parseLong(request.getParameter("duration"));
            
            Call call = new Call();
            call.setSubscriber(subscriber);
            call.setPrefix(opponent.getPrefix());
            call.setPhoneNum(phoneNum);
            long time = System.currentTimeMillis();
            call.setBeginTime(new Timestamp(time));
            call.setFinishTime(new Timestamp(time + duration*1000));
            call.setRate(rate);
            
            CallService callService = getServiceFactory().getCallService();
            callService.save(call);
        } catch (NumberFormatException | RetrieveException | FactoryException 
                | ServiceException | NullPointerException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return new Forwarder("/call/dial.html");
    }
}
