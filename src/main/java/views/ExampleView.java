package views;

public class ExampleView extends View{
    public ExampleView() {
        super("example");
    }

    @Override
    public void render() {
        //render is a series of souts and scanner.readLine() calls
        System.out.println("This is an example menu. We prompt the user, accept input, and do something.");
        System.out.println("Enter the name of a menu and we will navigate to that menu. This is probably very buggy.");
        String input = scanner.nextLine();
        navigate(input);
    }
}
