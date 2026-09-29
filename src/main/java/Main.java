import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        mainLoop:
        while(true) {
            System.out.print("$ ");
            String command = sc.nextLine();

            switch(command) {
                case String s when s.startsWith("type"):
                    String typeCommand = command.substring(5);

                    if (typeCommand.equals("echo") || typeCommand.equals("exit") || typeCommand.equals("type")){
                        System.out.printf("%s is a shell builtin\n", typeCommand);
                        break;
                    } else {
                        String path = System.getenv("PATH");
                        String[] pathDirs = path.split(":");
                        for(int i=0; 1<pathDirs.length; i++) {
                            File file = new File(pathDirs[i], typeCommand);
                            if (file.exists() && file.canExecute()) {
                                System.out.println(typeCommand + " is " + file.getAbsolutePath());
                                break;
                            } else if (!file.canExecute() || !file.exists()) {
                                System.out.println(typeCommand + ": not found");
                                break;
                            }
                        }
                    }
                    break;
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
