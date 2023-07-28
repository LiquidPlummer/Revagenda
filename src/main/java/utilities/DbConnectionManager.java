package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionManager {
    private Connection connection;

    public DbConnectionManager(String url, String username, String password, Class<?> driverClass) {
        if(org.h2.Driver.class.isAssignableFrom(driverClass)){
            //driver is an H2 database driver
            try {
                System.out.println("correct driver");
                this.connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
