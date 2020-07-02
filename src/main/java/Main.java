import atm.ATM;
import atm.ATMListener;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM(3, 8);
        atm.addPropertyChangeListener(new ATMListener(atm, 3, 8));
        System.out.println("Number of notes withdrawn: " + Arrays.toString(atm.withdrawCash(150)));

        System.out.println("Number of 50s: " + atm.getMax50s());
        System.out.println("Number of 20s: " + atm.getMax20s());
    }
}
