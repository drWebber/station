package controller.subscriber;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.interfaces.user.SubscriberService;
import controller.Action;
import controller.Forwarder;
import domain.user.Administrator;
import domain.user.Role;
import domain.user.Subscriber;
import exception.FactoryException;
import exception.ServiceException;

public class SubscriberSaveAction extends Action {
    @Override
    public Forwarder execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            //TODO: заменить энкодинг на фильтр
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) { }
        Subscriber subscriber = new Subscriber();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            subscriber.getUser().setId(id);
            subscriber.setId(id);
        } catch(NumberFormatException e) { }
        if (subscriber.getId() == null) {
            subscriber.getUser().setLogin(request.getParameter("login"));
            subscriber.getUser().setPassword(request.getParameter("password"));
            try {
                subscriber.getUser().cryptPassword();
            } catch (NoSuchAlgorithmException e) {
                throw new ServletException(e);
            }
        }
        subscriber.getUser().setSurname(request.getParameter("surname"));
        subscriber.getUser().setName(request.getParameter("name"));
        subscriber.getUser().setPatronymic(request.getParameter("patronymic"));
        subscriber.getUser().setRole(Role.SUBSCRIBER);
        subscriber.getUser().setActive(
                Boolean.parseBoolean(request.getParameter("isActive")));
        subscriber.setPassportId(request.getParameter("passportId"));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(request.getParameter("birthday"));
            subscriber.setBirthDay(new java.sql.Date(date.getTime()));
        } catch (ParseException e) {
            throw new ServletException(e);
        }
        Long adminId = null;
        Integer phoneNum = null;
        Integer prefixId = null;
        try {
            adminId = 14L; //TODO: заменить на взятие админа из сессии
            phoneNum = Integer.parseInt(request.getParameter("phoneNum"));
            prefixId = Integer.parseInt(request.getParameter("prefix"));
        } catch(NumberFormatException e) {
            throw new ServletException(e); 
        }
        
        subscriber.setAddress(request.getParameter("address"));
        subscriber.getPrefix().setId(prefixId);
        subscriber.setPhoneNum(phoneNum);
        
        //TODO: заменить на взятие админа из сессии
        Administrator administrator = new Administrator();
        administrator.setId(adminId);
        subscriber.setAdministrator(administrator);
        try {
            SubscriberService service = getServiceFactory().getSubscriberService();
            service.save(subscriber);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        
        return new Forwarder("/subscriber/list.html");
    }
}
