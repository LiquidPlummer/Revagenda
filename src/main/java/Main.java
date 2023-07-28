import daos.UserDao;
import models.User;
import utilities.DbConnectionManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbConnectionManager dbConnectionManager = new DbConnectionManager("jdbc:h2:mem:", "sa", "", org.h2.Driver.class);
        Connection connection = dbConnectionManager.getConnection();
        UserDao userDao = new UserDao(connection);

        User user1 = new User("asdasd", "sd", "Kyle", "sgh5", "kyledseshgsdfhom");
        User user2 = new User("sdfgsdg", "rtghjrt", "fdghdfnb", "dfgsd", "sddfhshhvatfhdscom");
        User user3 = new User("kplummer", "password", "Kyle", "Plummer", "kyle.plummer@revature.com");

        user1 = userDao.create(user1);
        user2 = userDao.create(user2);
        user3 = userDao.create(user3);



        User queryUser = userDao.findUserByUsername("kplummer");
        System.out.println(queryUser.getId());
        System.out.println(queryUser.getFirstName());
        System.out.println(queryUser.getLastname());
        System.out.println(queryUser.getEmail());


    }
}
