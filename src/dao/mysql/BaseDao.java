package dao.mysql;

import java.sql.Connection;

public class BaseDao {
    private Connection connection;

    public BaseDao(Connection connection) {
        super();
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
