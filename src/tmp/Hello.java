package tmp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.datasource.MysqlConnector;
import station.dao.mysql.UserDaoImpl;
import station.exception.DaoException;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
	        HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer = response.getWriter();
	    
//	    for (int i = 0; i < 100; i++) {
	        Connection conn = null;
	        try {
	            conn = MysqlConnector.getConnection();
	            
	            UserDaoImpl dao = new UserDaoImpl();
	            dao.setConnection(conn);
	            
	            System.out.println(dao.read(1L));
	            
//	            String sqlScript = "SELECT * FROM `users`"; 
//	            Statement statement = null;
//	            ResultSet resultSet = null;
//	            statement = conn.createStatement();
//	            resultSet = statement.executeQuery(sqlScript);
//	            while (resultSet.next()) {
//	                writer.print("<p style='color:#383'>" +
//	                        resultSet.getString("fullName"));
//	            }
//	            resultSet.close();
//	            statement.close();
	        } catch (SQLException | NamingException | DaoException e) {
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
//	    }
        writer.close();
	}
}
