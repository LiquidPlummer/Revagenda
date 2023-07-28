package views;

import utilities.ConsoleRenderer;

import java.util.Scanner;

public abstract class View {
    String name;
    Scanner scanner;
    ConsoleRenderer consoleRenderer;

    public View(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public void navigate(String menu) {
        consoleRenderer.navigate(menu);
    }

    public String getName() {
        return this.name;
    }

    public void setConsoleRenderer(ConsoleRenderer consoleRenderer) {
        this.consoleRenderer = consoleRenderer;
    }

    public abstract void render();
}
