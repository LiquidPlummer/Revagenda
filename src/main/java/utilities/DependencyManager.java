package utilities;

import daos.Dao;
import daos.TaskDao;
import daos.UserDao;

import java.util.LinkedList;
import java.util.List;

public class DependencyManager {
    private static DependencyManager dependencyManagerSingleton;
    private final List<Dao> daoList;
    private final DatabaseManager databaseManager;

    private DependencyManager() {
        this.databaseManager = new DatabaseManager("jdbc:h2:mem:", "sa", "", org.h2.Driver.class);

        this.daoList = new LinkedList<>();
        daoList.add(new UserDao(this.databaseManager.getConnection()));
        daoList.add(new TaskDao(this.databaseManager.getConnection()));
    }

    public static DependencyManager get() {
        if(dependencyManagerSingleton == null) {
            dependencyManagerSingleton = new DependencyManager();
        }
        return dependencyManagerSingleton;
    }

    public Dao getDao(Class<?> daoClass) {
        for (Dao temp: daoList) {
            if(temp.getClass().isAssignableFrom(daoClass)) {
                return temp;
            }
        }

        return null;
    }
}
