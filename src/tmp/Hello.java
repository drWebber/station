package tmp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.datasource.Connector;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
	        HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer = response.getWriter();
	    
	    for (int i = 0; i < 100; i++) {
	        Connection conn = null;
	        try {
	            conn = Connector.getConnection();
	            String sqlScript = "SELECT * FROM `users`"; 
	            Statement statement = null;
	            ResultSet resultSet = null;
	            statement = conn.createStatement();
	            resultSet = statement.executeQuery(sqlScript);
	            while (resultSet.next()) {
	                writer.print("<p style='color:#333'>" +
	                        resultSet.getString("fullName"));
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
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
	    }
        writer.close();
	}
}
