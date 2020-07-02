package atm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ATM {

    private int max50s;
    private int max20s;
    private int totalBalance;
    private static final int NOTE_50 = 50;
    private static final int NOTE_20 = 20;


    public ATM(int numberOf50s, int numberOf20s) {
        this.max50s = numberOf50s;
        this.max20s = numberOf20s;
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
        int[] result = withdrawRecursive(0, 0, amount, max50s);
        if (result != null) {
            max50s = max50s - result[0];
            max20s = max20s - result[1];
            totalBalance = max50s * NOTE_50 + max20s * NOTE_20;
        }
        return result;
    }


    public int[] withdrawRecursive(int num50s, int num20s, int amount, int total50s) {
        int currentTotal = num50s * NOTE_50 + num20s * NOTE_20;
        if (currentTotal == amount) {
            return new int[]{num50s, num20s};
        }

        if (num50s < total50s) {
            if (amount - currentTotal >= NOTE_50) {
                log.info("adding 50");
                return withdrawRecursive(num50s + 1, num20s, amount, total50s);
            }
            log.info("maxing 50");
            return withdrawRecursive(num50s, num20s, amount, num50s);
        }

        if (amount - currentTotal >= NOTE_20) {
            log.info("adding 20");
            return withdrawRecursive(num50s, num20s + 1, amount, total50s);
        }

        int remainingAmount = amount - currentTotal;
        log.info("remaining amount: " + remainingAmount);

        if (remainingAmount > 0 && num50s > 0) {
            log.info("decreasing 50s");
            return withdrawRecursive(num50s - 1, num20s, amount, num50s - 1);
        }

        return null;
    }


    public int getTotalBalance() {
        return totalBalance;
    }

    public int getMax50s() {
        return max50s;
    }

    public int getMax20s() {
        return max20s;
    }
}
