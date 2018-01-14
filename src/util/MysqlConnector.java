package util;

import java.sql.SQLException;
import java.sql.Connection;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class MysqlConnector {
    private static volatile DataSource ds = null;
    private static final String PROJECT_NAME = "station";

    public static Connection getConnection()
            throws NamingException, SQLException {
        Connection conn = null;
            if (ds == null) {
                synchronized (MysqlConnector.class) {
                    if (ds == null) {
                        ds = InitialContext
                                .<DataSource>doLookup("java:comp/env/jdbc/"
                                        + PROJECT_NAME);
                    }
                }
            }
            conn = ds.getConnection();
        return conn;
    }
}
