package test.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnector {
	private static String jdbcUrl = "jdbc:mysql://localhost:3307/station?"
	        + "useUnicode=true&characterEncoding=UTF-8";
	private static String jdbcUser = "station_user";
	private static String jdbcPassword = "123321";
	
	public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword) throws ClassNotFoundException {
		
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
	}
}