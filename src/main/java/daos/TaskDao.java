package daos;

import models.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskDao implements Dao<Task>{
    private final Connection connection;

    public TaskDao(Connection connection) {
        this.connection = connection;

        /*
        Because the database is embedded and volatile, we need to initialize the schema when the app starts. Later when
        a non-volatile database is available to us, we can change this to initialize the schema just once.
        */
        String sql = "CREATE TABLE tasks(id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(200), " +
                "description VARCHAR(2000), due VARCHAR(200), fk_user_id INT," +
                "FOREIGN KEY (fk_user_id) references users(id))";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Task table created.");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Task create(Task task) {
        return null;
    }

    @Override
    public Task findById(int id) {
        return null;
    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(int id) {

    }
}
