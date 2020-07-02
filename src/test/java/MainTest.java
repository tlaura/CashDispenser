import atm.ATM;
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
    public void testAmount_70_50Note() {
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
        Assertions.assertEquals(4, result100[1]);
    }

    @Test
    public void testAmount_200() {
        int[] result200 = atm.withdrawCash(200);
        Assertions.assertEquals(5, result200[1]);
    }
}
