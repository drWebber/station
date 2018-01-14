package validator.impl;

import javax.servlet.http.HttpServletRequest;

import validator.BaseValidator;
import validator.Validator;
import domain.service.Offer;
import exception.IncorrectFormDataException;
import exception.ValidatorException;

public class OfferValidator extends BaseValidator implements Validator<Offer> {

    public OfferValidator(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Offer validate()
            throws IncorrectFormDataException, ValidatorException {
        Offer offer = new Offer();
        try {
            offer.setId(getIntegerParameter("id"));
        } catch (IncorrectFormDataException e) {
            /* do nothing, because `id` can be null on create action */
        }
        offer.setName(getStringParameter("name"));
        offer.setDescription(getStringParameter("description"));

        offer.setMonthlyFee(getFloatParameter("monthlyFee"));
        offer.setSubscriptionRate(getFloatParameter("subscriptionRate"));

        offer.setRequired(Boolean.parseBoolean(
                request.getParameter("required")));
        return offer;
    }
}
