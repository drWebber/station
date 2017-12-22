package tmp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.domain.user.Administrator;
import station.domain.user.Prefix;
import station.domain.user.Role;
import station.domain.user.Subscriber;
import station.exception.ServiceException;
import station.service.ServiceLocator;
import station.service.interfaces.user.SubscriberService;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
	        HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer = response.getWriter();
	    
        ServiceLocator locator = null;
        try {
            locator = new ServiceLocator();
            SubscriberService service = locator
                    .getService(SubscriberService.class);
            Subscriber subscriber = new Subscriber();
            subscriber.setLogin("vasiya93");
            subscriber.setPassword("qwerty");
            subscriber.setSurname("Васильев");
            subscriber.setName("Василий");
            subscriber.setPatronymic("Васильевич");
            subscriber.setRole(Role.SUBSCRIBER);
            subscriber.setActivityState(true);
            subscriber.setPassportId("040689AE");
            subscriber.setBirthDay(new Date(0));
            subscriber.setAddress("210033, ул. Гагарина 10, кв. 7");
            Prefix pref = new Prefix();
            pref.setId(212);
            subscriber.setPrefix(pref);
            subscriber.setPhoneNum(240003);
            Administrator admin = new Administrator();
            admin.setId(14L);
            subscriber.setAdministrator(admin);
            service.save(subscriber);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
	    
        writer.close();
	}
}
