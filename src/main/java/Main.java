import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        mainLoop:
        while(true) {
            System.out.print("$ ");
            String command = sc.nextLine();

            switch(command) {
                case String s when s.startsWith("echo"):
                    String echoString = command.substring(5);
                    System.out.println(echoString);
                    break;
                case "exit":
                    break mainLoop;
                default:
                    System.out.printf("%s: command not found\n", command);
                    break;
            }


        }
    }
}
