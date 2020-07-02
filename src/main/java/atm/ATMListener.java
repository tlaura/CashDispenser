package atm;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Getter
@Setter
@Slf4j
public class ATMListener implements PropertyChangeListener {
    private int max50s;
    private int max20s;
    private ATM atm;
    private int maxTopup50;
    private int maxTopup20;


    public ATMListener(ATM atm, int maxTopup50, int maxTopup20) {
        this.atm = atm;
        this.maxTopup50 = maxTopup50;
        this.maxTopup20 = maxTopup20;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("num50sRemaining")) {
            this.setMax50s((int) propertyChangeEvent.getNewValue());
            if (this.max50s < 1) {
                log.info("Number of 50 notes topped up to max: " + maxTopup50);
                atm.setMax50s(maxTopup50);
            }
        }
        if(propertyChangeEvent.getPropertyName().equals("num20sRemaining")) {
            this.setMax20s((int) propertyChangeEvent.getNewValue());
            if(this.max20s < 1) {
                log.info("Number of 20 notes topped up to max: " + maxTopup20);
                atm.setMax20s(maxTopup20);
            }
        }
    }
}
