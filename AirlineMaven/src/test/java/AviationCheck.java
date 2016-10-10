import com.slabadniak.task2.airline.EpamAirline;
import com.slabadniak.task2.exeption.IncorrectDataExeption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AviationCheck {
    private EpamAirline airline;
    private int lowValue;
    private int highValue;

    public AviationCheck(int lowValue, int highValue) {
        airline = EpamAirline.getAirline();
        this.lowValue = lowValue;
        this.highValue = highValue;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> controlTraingles() {
        return Arrays.asList(new Object[][]{
                {-100, 111},
                {111, -233},
                {2000, 1999},
                {1, 2},
                {1000, 20000},
        });
    }

    @Test(expected = IncorrectDataExeption.class)
    public void fuelConsumptionCheck() throws IncorrectDataExeption {
        airline.fuelConsumptionLimit(lowValue, highValue);
    }
}
