package station.datasource;

import java.sql.SQLException;
import java.sql.Connection;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class Connector {
    private static volatile InitialContext initContext = null;
    private static volatile DataSource ds = null;
    private static final String PROJECT_NAME = "station";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            if (initContext == null || ds == null) {
                synchronized (Connector.class) {
                    if (initContext == null || ds == null) {
                        initContext = new InitialContext();
                        ds = (DataSource) initContext
                                .lookup("java:comp/env/jdbc/" + PROJECT_NAME);
                    }
                }
            }
            conn = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
