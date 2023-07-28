package views;

import utilities.ConsoleRenderer;

public class StartView extends View{
    public StartView() {
        super("start");
    }

    @Override
    public void render() {
        System.out.println("Welcome to Revagenda!\n\n" +
                "=============== Main Menu ===============\n" +
                "R) Register\n" +
                "L) login\n" +
                "Q) Quit");

        String input = scanner.nextLine();

        switch(input) {
            case "R":
            case "r":
                navigate("register");
                break;
            case "L":
            case "l":
                navigate("login");
                break;
            case "Q":
            case "q":
                System.out.println("Have a great day!");
                consoleRenderer.quit();
        }


    }
}
