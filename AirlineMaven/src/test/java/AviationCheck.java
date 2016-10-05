import com.slabadniak.task2.airline.EpamAirlines;
import com.slabadniak.task2.exeption.InvalidArgumentExeption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AviationCheck {
    private EpamAirlines airline;
    private int lowValue;
    private int highValue;

    public AviationCheck(int lowValue, int highValue){
        airline = EpamAirlines.getAirline();
        this.lowValue = lowValue;
        this.highValue = highValue;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> controlTraingles() {
        return Arrays.asList(new Object[][] {
                { -100,111 },
                {111, -233 },
                { 2000, 1999 },
                { 1, 2 },
                { 1000, 20000 },
        });
    }

    @Test(expected = InvalidArgumentExeption.class)
    public void fuelConsumptionCheck() throws InvalidArgumentExeption {
        airline.getAviation().fuelConsumptionLimit(lowValue,highValue);

    }

}
