import utilities.ConsoleRenderer;
import utilities.DependencyManager;

public class Main {
    public static void main(String[] args) {
        DependencyManager.get();
        ConsoleRenderer console = new ConsoleRenderer();
        console.start();



    }
}
