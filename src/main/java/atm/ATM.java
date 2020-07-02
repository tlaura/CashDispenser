package atm;

import exceptions.NotesUnavailableForAmountException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Slf4j
@Getter
public class ATM {

    private int max50s;
    private int max20s;
    private int totalBalance;
    private static final int NOTE_50 = 50;
    private static final int NOTE_20 = 20;
    private PropertyChangeSupport support;


    public ATM(int numberOf50s, int numberOf20s) {
        this.max50s = numberOf50s;
        this.max20s = numberOf20s;
        this.totalBalance = (numberOf20s * NOTE_20) + (numberOf50s * NOTE_50);
        support = new PropertyChangeSupport(this);
    }


    public int[] withdrawCash(int amount) {
        if (amount % 10 != 0) {
            throw new NotesUnavailableForAmountException("This ATM can only supply 20 and 50 notes. Add correct amount.");
        }
        if (amount > totalBalance) {
            throw new NotesUnavailableForAmountException("ATM's balance is too low for requested amount. Maximum balance to withdraw: " + totalBalance);
        }
        if (amount < 20) {
            throw new NotesUnavailableForAmountException("Amount requested is too low.");
        }
        int[] result = withdrawRecursive(0, 0, amount, max50s);
        if (result != null) {
            setMax50s(max50s - result[0]);
            setMax20s(max20s - result[1]);
            setTotalBalance(max50s, max20s);
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
                return withdrawRecursive(num50s + 1, num20s, amount, total50s);
            }
            return withdrawRecursive(num50s, num20s, amount, num50s);
        }

        if (amount - currentTotal >= NOTE_20) {
            return withdrawRecursive(num50s, num20s + 1, amount, total50s);
        }

        int remainingAmount = amount - currentTotal;

        if (remainingAmount > 0 && num50s > 0) {
            return withdrawRecursive(num50s - 1, num20s, amount, num50s - 1);
        }
        throw new NotesUnavailableForAmountException("This ATM can only supply 50 and 20 notes.");
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setMax50s(int max50s) {
        int oldValue = this.max50s;
        this.max50s = max50s;
        support.firePropertyChange("num50sRemaining", oldValue, max50s);
        setTotalBalance(this.max50s, this.max20s);
    }

    public void setMax20s(int max20s) {
        int oldValue = this.max20s;
        this.max20s = max20s;
        support.firePropertyChange("num20sRemaining", oldValue, max20s);
        setTotalBalance(this.max50s, this.max20s);
    }

    void setTotalBalance(int num50s, int num20s) {
        this.totalBalance = num50s * NOTE_50 + num20s * NOTE_20;
    }
}
