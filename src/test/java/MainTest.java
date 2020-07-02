import atm.ATM;
import exceptions.NotesUnavailableForAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    private ATM atm;

    @BeforeEach
    public void init() {
        atm = new ATM(3, 8);
    }

    @Test
    public void testAmount_20() {
        int[] result20 = atm.withdrawCash(20);
        Assertions.assertEquals(1, result20[1]);
    }

    @Test
    public void testAmount_40() {
        int[] result40 = atm.withdrawCash(40);
        Assertions.assertEquals(2, result40[1]);
    }

    @Test
    public void testAmount_50() {
        int[] result50 = atm.withdrawCash(50);
        Assertions.assertEquals(1, result50[0]);
    }

    @Test
    public void testAmount_70() {
        int[] result70 = atm.withdrawCash(70);
        Assertions.assertEquals(1, result70[0]);
        Assertions.assertEquals(1, result70[1]);
    }

    @Test
    public void testAmount_80() {
        int[] result80 = atm.withdrawCash(80);
        Assertions.assertEquals(4, result80[1]);
    }

    @Test
    public void testAmount_100() {
        int[] result100 = atm.withdrawCash(100);
        Assertions.assertEquals(2, result100[0]);
    }

    @Test
    public void testAmount_150() {
        int[] result150 = atm.withdrawCash(150);
        Assertions.assertEquals(3, result150[0]);
    }

    @Test
    public void testAmount_60() {
        int[] result60 = atm.withdrawCash(60);
        Assertions.assertEquals(3, result60[1]);
    }

    @Test
    public void testAmount_110() {
        int[] result110 = atm.withdrawCash(110);
        Assertions.assertEquals(1, result110[0]);
        Assertions.assertEquals(3, result110[1]);
    }

    @Test
    public void testAmount_200() {
        int[] result200 = atm.withdrawCash(200);
        Assertions.assertEquals(5, result200[1]);
    }

    @Test
    public void testAvailable50s_OnWithdraw200() {
        atm.withdrawCash(200);
        int avail50s = atm.getMax50s();
        Assertions.assertEquals(1, avail50s);
    }

    @Test
    public void testAvailable50s_OnWithdraw110() {
        atm.withdrawCash(110);
        int avail50s = atm.getMax50s();
        Assertions.assertEquals(2, avail50s);
    }

    @Test
    public void testAvailable50s_OnWithdraw150() {
        atm.withdrawCash(150);
        int avail50s = atm.getMax50s();
        Assertions.assertEquals(0, avail50s);
    }

    @Test
    public void testAvailable20s_OnWithdraw80() {
        atm.withdrawCash(80);
        int avail20s = atm.getMax20s();
        Assertions.assertEquals(4, avail20s);
    }

    @Test
    public void testAvailable20s_OnWithdraw200() {
        atm.withdrawCash(200);
        int avail20s = atm.getMax20s();
        Assertions.assertEquals(3, avail20s);
    }

    @Test
    public void testTotalBalance_OnWithdraw200() {
        atm.withdrawCash(200);
        int balance = atm.getTotalBalance();
        Assertions.assertEquals(110, balance);
    }

    @Test
    public void testIncorrectAmount_30() {
        Assertions.assertThrows(NotesUnavailableForAmountException.class, () -> {
            atm.withdrawCash(30);
        });
    }

    @Test
    public void testIncorrectAmount_10() {
        Assertions.assertThrows(NotesUnavailableForAmountException.class, () -> {
            atm.withdrawCash(10);
        });
    }
}
