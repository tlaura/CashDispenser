import java.util.Scanner;

public class UI {
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public int getValueFromCommandLine(String commandPrompt) {
        System.out.println(commandPrompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Add numeric value");
        }
    }


    public void printWithdrawal(String commandPrompt, int notes) {
        System.out.println(commandPrompt + notes);
    }
}
