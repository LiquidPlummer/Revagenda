package utilities;

import daos.TaskDao;
import daos.UserDao;
import models.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private final Connection connection;

    public DatabaseManager(String url, String username, String password, Class<?> driverClass) {
        if(org.h2.Driver.class.isAssignableFrom(driverClass)){
            //driver is an H2 database driver
            try {
                this.connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.connection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
