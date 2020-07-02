package atm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ATM {

    private int total50s;
    private int total20s;
    private int totalBalance;
    private static final int NOTE_50 = 50;
    private static final int NOTE_20 = 20;


    public ATM(int numberOf50s, int numberOf20s) {
        this.total50s = numberOf50s;
        this.total20s = numberOf20s;
        this.totalBalance = (numberOf20s * NOTE_20) + (numberOf50s * NOTE_50);
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
        int[] result = withdrawRecursive(0, 0, amount, total50s, total20s);
        if (result != null) {
            total50s = total50s - result[0];
            total20s = total20s - result[1];
            totalBalance = total50s * NOTE_50 + total20s * NOTE_20;
        }
        return result;
    }


    public int[] withdrawRecursive(int num50s, int num20s, int amount, int total50s, int total20s) {
        if (amount == 0) {
            return new int[]{num50s, num20s};
        }

        int currentTotal = num50s * NOTE_50 + num20s * NOTE_20;
        if (currentTotal == amount) {
            return new int[]{num50s, num20s};
        }

//        check if we can take 50s, if yes, recurse
        if (num50s < total50s) {
            if (amount - currentTotal >= NOTE_50) {
                log.info("adding 50");
                return withdrawRecursive(num50s + 1, num20s, amount, total50s, total20s);
            }
            log.info("maxing 50");
            return withdrawRecursive(num50s, num20s, amount, num50s, total20s);
        }

        if (num20s < total20s) {
            if (amount - currentTotal >= NOTE_20) {
                log.info("adding 20");
                return withdrawRecursive(num50s, num20s + 1, amount, total50s, total20s);
            }
            log.info("maxing 20s");
            return withdrawRecursive(num50s, num20s, amount, total50s, num20s);
        }

        int remainingAmount = amount - currentTotal;
        log.info("remaining amount: " + remainingAmount);

        if (remainingAmount > 0 && num50s > 0) {
            log.info("decreasing 50s");
            return withdrawRecursive(num50s - 1, num20s, amount, num50s - 1, this.total20s);
        }
        return null;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public int getTotal50s() {
        return total50s;
    }

    public int getTotal20s() {
        return total20s;
    }
}
