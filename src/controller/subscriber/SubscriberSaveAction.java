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
import domain.user.Prefix;
import domain.user.Role;
import domain.user.Subscriber;
import domain.user.User;
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
        User user = new User();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            user.setId(id);
            subscriber.setId(id);
        } catch(NumberFormatException e) { }
        if (subscriber.getId() == null) {
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            try {
                user.cryptPassword();
            } catch (NoSuchAlgorithmException e) {
                throw new ServletException(e);
            }
        }
        user.setSurname(request.getParameter("surname"));
        user.setName(request.getParameter("name"));
        user.setPatronymic(request.getParameter("patronymic"));
        user.setRole(Role.SUBSCRIBER);
        user.setActive(Boolean.parseBoolean(request.getParameter("isActive")));
        subscriber.setUser(user);
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
            //adminId = Long.parseLong(request.getParameter("adminId"));
            adminId = 14L; //TODO: заменить на парсинг из параметра
            phoneNum = Integer.parseInt(request.getParameter("phoneNum"));
            prefixId = Integer.parseInt(request.getParameter("prefix"));
        } catch(NumberFormatException e) {
            throw new ServletException(e); 
        }
        
        subscriber.setAddress(request.getParameter("address"));
        Prefix prefix = new Prefix();
        prefix.setId(prefixId);
        subscriber.setPrefix(prefix);
        subscriber.setPhoneNum(phoneNum);
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
