import atm.ATM;
import atm.ATMListener;

public class Main {

    public static void main(String[] args) {
        UI ui = new UI();

        int max50s = ui.getValueFromCommandLine("Add initial number of 50 notes in the ATM: ");
        int max20s = ui.getValueFromCommandLine("Add initial number of 20 notes in the ATM: ");

        ATM atm = new ATM(max50s, max20s);
        atm.addPropertyChangeListener(new ATMListener(atm, max50s, max20s));

        int amount = ui.getValueFromCommandLine("Withdraw cash amount: ");

        int[] notes = atm.withdrawCash(amount);
        ui.printWithdrawal("Number of 50 notes withdrawn: ", notes[0]);
        ui.printWithdrawal("Number of 20 notes withdrawn: ", notes[1]);
    }
}
