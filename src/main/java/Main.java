import atm.ATM;
import atm.ATMListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

        log.info("Available 50s in the ATM: " + atm.getMax50s());
        log.info("Available 20s in the ATM: " + atm.getMax20s());
    }
}
