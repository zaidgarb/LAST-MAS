import java.util.Scanner;

public class UserAgent {
    ManagerAgent managerAgent;
    Scanner sc;

    public UserAgent(ManagerAgent managerAgent) {
        this.managerAgent = managerAgent;
        sc = new Scanner(System.in);
        runCycle();
    }

    private void runCycle() {
        System.out.println("Types of command:\nI_<configuration file>\nD_<configuration file>\nE (exit)");
        boolean run = true;
        while (run) {
            System.out.println("Enter command:");
            String ans = sc.nextLine();
            String[] parsedAns = ans.split("_");
            switch (parsedAns[0]) {
                case "I":
                    try {
                        if (managerAgent.initialize(parsedAns[1])) {
                            System.out.println("System initialized");
                        } else {
                            System.out.println("System failed");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("There some problem with file");
                    }
                    break;
                case "D":
                    managerAgent.evaluate(parsedAns[1]);
                    break;
                case "E":
                    System.out.println("Exiting...");
                    run = false;
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}
