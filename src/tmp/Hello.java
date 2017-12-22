package tmp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.dao.mysql.SubscriberDaoImpl;
import station.dao.mysql.UserDaoImpl;
import station.datasource.MysqlConnector;
import station.domain.user.Administrator;
import station.domain.user.Prefix;
import station.domain.user.Role;
import station.domain.user.Subscriber;
import station.exception.ServiceException;
import station.service.impl.user.SubscriberServiceImpl;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
	        HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer = response.getWriter();
	    
	    Connection conn = null;
        try {
            conn = MysqlConnector.getConnection();
            
            SubscriberDaoImpl subDao = new SubscriberDaoImpl(conn);
            
            UserDaoImpl userDao = new UserDaoImpl(conn);
            
            SubscriberServiceImpl service = new SubscriberServiceImpl();
            service.setSubscriberDao(subDao);
            service.setUserDao(userDao);
            
            Subscriber subscriber = service.getById(16L);
            writer.append(subscriber.toString());
            
            subscriber.setSurname("Петров");
            subscriber.setName("Петр");
            subscriber.setPatronymic("Петрович");
            service.save(subscriber);
            
            Subscriber sub2 = new Subscriber();
            sub2.setActivityState(true);
            sub2.setAddress("ул. Ленинградская 88, оф. 6");
            Administrator admin = new Administrator();
            admin.setId(14L);
            sub2.setAdministrator(admin);
            sub2.setPassportId("12k3j");
            sub2.setBirthDay(new Date(1));
            sub2.setSurname("Олегов");
            sub2.setName("Олег");
            sub2.setPatronymic("Олегович");
            sub2.setLogin("Oleg");
            sub2.setPassword("123");
            Prefix prefix = new Prefix();
            prefix.setId(212);
            sub2.setPrefix(prefix);
            sub2.setPhoneNum(240317);
            sub2.setRole(Role.SUBSCRIBER);
            service.save(sub2);
            
        } catch (NamingException | SQLException | ServiceException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
	    
        writer.close();
	}
}
