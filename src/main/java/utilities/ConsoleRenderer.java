package utilities;

import views.ExampleView;
import views.RegisterView;
import views.StartView;
import views.View;

import java.util.LinkedList;
import java.util.List;

public class ConsoleRenderer {
    private final List<View> menuList;
    private View nextMenu;
    private boolean quit;

    public ConsoleRenderer() {
        this.quit = false;
        menuList = new LinkedList<>();
        registerView(new ExampleView());
        registerView(new StartView());
        registerView(new RegisterView());
    }

    public void start() {
        navigate("start");

        while(!quit) {
            nextMenu.render();
        }
    }

    public void navigate(String menu) {
        for(View temp : menuList) {
            if(temp.getName().equals(menu)) {
                nextMenu = temp;
                break;
            }
        }
    }

    public void registerView(View view) {
        view.setConsoleRenderer(this);
        menuList.add(view);
    }

    public void quit() {
        this.quit = true;
    }

}
