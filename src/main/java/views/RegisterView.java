package views;

import daos.UserDao;
import models.User;
import utilities.DependencyManager;

public class RegisterView extends View{
    private final UserDao userDao;
    public RegisterView() {
        super("register");
        this.userDao = (UserDao)DependencyManager.get().getDao(UserDao.class);
    }

    @Override
    public void render() {

    }
}
