package validator.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import util.service.CallDirection;
import util.service.CallRateResolver;
import util.user.UserRetriever;
import validator.BaseValidator;
import validator.Validator;
import domain.service.Call;
import domain.service.RateType;
import domain.user.Subscriber;
import exception.RetrieveException;
import exception.validator.IncorrectFormDataException;
import exception.validator.ValidatorException;

public class CallValidator extends BaseValidator implements Validator<Call> {
    public CallValidator(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Call validate()
            throws IncorrectFormDataException, ValidatorException {
        Call call = new Call();

        Integer prefixId = getIntegerParameter("prefix");
        Integer phoneNum = getIntegerParameter("phoneNum");
        Long duration = getLongParameter("duration");

        /* current Subscriber */
        Subscriber subscriber = null;
        try {
            subscriber =
                    new UserRetriever<Subscriber>(request).getCurrentUser();
        } catch (RetrieveException e) {
            logger.error(e);
            throw new ValidatorException(e);
        }

        /* Opponent - opposite wire end subscriber */
        Subscriber opponent = new Subscriber();
        opponent.getPrefix().setId(prefixId);

        /* Call direction INCOMING|OUTGOING */
        CallDirection direction =
                CallDirection.valueOf(request.getParameter("direction"));

        /* Call rate type determining */
        RateType rateType =
                new CallRateResolver(subscriber, opponent, direction)
                .getResolvedRate();
        call.setRateType(rateType);

        call.setSubscriber(subscriber);
        call.setPrefix(opponent.getPrefix());
        call.setPhoneNum(phoneNum);
        long time = System.currentTimeMillis();
        call.setBeginTime(new Timestamp(time));
        call.setFinishTime(new Timestamp(time + duration * 1000));
        return call;
    }
}
