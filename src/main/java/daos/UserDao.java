package daos;

import models.User;

import java.sql.*;

/*
This is a Data Access Object (DAO). A DAO contains the connection details and code to preform operations on
a database (or more accurately: a non-volatile data store). A DAO is tightly coupled with the type of object it
operates on, so this UserDao should contain everything the application needs to preform CRUD on User objects. A DAO
is generally specific to one type of data store, so if we had multiple persistence solutions we might have multiple
DAOs. Later we will "wrap" these DAOs with Spring Data Repositories, and abstract ourselves away from those specifics.
 */
public class UserDao {
    //A DAO should contain the connection to the data store. We encapsulate all necessary components for CRUD
    private final Connection connection;

    /*
    Dependency injection. This allows us to encapsulate the connection here within the DAO, but keeps it
    "loosely coupled" so we can easily swap out for a different kind of connection if need be.
     */
    public UserDao(Connection connection) {
        this.connection = connection;

        /*
        Because the database is embedded and volatile, we need to initialize the schema when the app starts. Later when
        a non-volatile database is available to us, we can change this to initialize the schema just once.
         */
        String sql = "CREATE TABLE users(id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(200) UNIQUE, " +
                "password VARCHAR(200), first_name VARCHAR(200), last_name VARCHAR(200), email VARCHAR(200))";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Users table created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    This is the pattern for inserting/updating data with JDBC. If we are auto generating keys, and we want those keys,
    we can include the `Statement.RETURN_GENERATED_KEYS` flag and extract them as a ResultSet from the statement object.
    1. Create the SQL string
    2. Prepare and parameterize the statement
    3. Execute the statement
    4. (Optional) Retrieve and extract the auto generated keys
    5. (Optional) Return the object

    This method persists a new row in the database. We are expecting the calling code to pass us a User object, and
    when we are finished we will return that user object (with newly populated id) to the calling code.
     */
    public User create(User user) {
        //Create the SQL statement string
        String sql = "INSERT INTO users (username, password, first_name, last_name, email)" +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            //Turn the sql string into a statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //parameterize the statement
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getEmail());

            //execute the statement
            preparedStatement.executeUpdate();

            //retrieve the auto generated keys
            ResultSet rs = preparedStatement.getGeneratedKeys();

            //marshall the object
            if(rs.next()) {
                user.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //return the object, which now includes the key assigned by the database
        return user;
    }

    /*
    This is the pattern for a JDBC query. Generally all queries for info will follow a similar pattern. The most
    confusing part here is the result set, which must be iterated over once for each result.
    1. Create the SQL string
    2. Prepare and parameterize the statement
    3. Execute the statement and store the result set
    4. Extract the data from the result set, store in the object (perhaps looping through many results)
    5. Return the object
     */
    public User findUserByUsername(String username) {
        User user = new User();

        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "kplummer");
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    /*
    Instead of writing out numerous methods to update each individual column, we can just have one update method that
    updates all fields. This results in overwriting much of the same data each time. There is a performance cost for
    doing so. If we needed to optimize this we could split it into several updates, but this would save probably
    a trivial amount of time.

    This follows the same basic pattern as the create() method above.
     */
    public void update(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
