package atm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ATM {

    private int total50s;
    private int total20s;
    private int totalBalance;

    public ATM(int numberOf50s, int numberOf20s) {
        this.total50s = numberOf50s;
        this.total20s = numberOf20s;
        this.totalBalance = (numberOf20s * 20) + (numberOf50s * 50);
    }

    public int[] withdrawCash(int amount) {
        if (amount % 10 != 0) {
            return null;
        }
        if (amount > totalBalance) {
            return null;
        }
        if (amount < 20) {
            return null;
        }
        return null;
    }


    public int getTotal50s() {
        return total50s;
    }

    public int getTotal20s() {
        return total20s;
    }
}
