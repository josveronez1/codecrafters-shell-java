import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        mainLoop:
        while(true) {
            System.out.print("$ ");
            String command = sc.nextLine();

            String[] parts = command.split(" ");
            String baseCommand = parts[0];

            switch(command) {
                case String s when s.startsWith("type"):
                    String typeCommand = command.substring(5);

                    if (typeCommand.equals("echo") || typeCommand.equals("exit") || typeCommand.equals("type")){
                        System.out.printf("%s is a shell builtin\n", typeCommand);
                        break;
                    } else {
                        String path = System.getenv("PATH");
                        String[] pathDirs = path.split(":");
                        boolean found = false;
                        for(int i=0; i<pathDirs.length; i++) {
                            File file = new File(pathDirs[i], typeCommand);

                            if (file.exists() && file.canExecute()) {
                                found = true;
                                System.out.println(typeCommand + " is " + file.getAbsolutePath());
                                break;
                            }

                        }
                        if (!found) {
                            System.out.println(typeCommand + ": not found");
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
                    if(baseCommand != null && getPath(baseCommand) != null){
                        System.out.printf("\nProgram was passed %d args (including program name).", parts.length);
                    } else {
                        System.out.printf("\n%s: command not found", command);
                    }
                    break;
            }
        }
    }
    public static String getPath(String comm) {
        String path = System.getenv("PATH");
        String[] dirs = path.split(":");

        for (String dir : dirs) {
            File file = new File(dir, comm);

            if(file.exists() && file.canExecute()) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }
}
