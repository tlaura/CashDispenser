import atm.ATM;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM(3, 8);
        System.out.println("No of notes withdrawn: " + Arrays.toString(atm.withdrawCash(70)));

        System.out.println("No of 50s: " + atm.getTotal50s());
        System.out.println("No of 20s: " + atm.getTotal20s());
    }
}
