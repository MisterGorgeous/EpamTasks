import com.slabadniak.task2.datachecker.DataChecker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegexCheck {
    private String planeInfo;

    public RegexCheck(String planeInfo) {
        this.planeInfo = planeInfo;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> controlTraingles() {
        return Arrays.asList(new Object[][]{
                {"TY154 180 47.90f 37.55f 950 2650  39750 95.00f 2100"},
                {"BOEING747 36GWSFSD6 69.20f 17.30f 57.90f 1024 9FSDFSF900 27562 JT9D 213.07f 401SDGSDGFSDF0"},
                {"748 388 70.60f 17.60f 59.60f 955 9800 25552 JT9D 213.07f 5020"},
                {"AH124 28 120.00f 69.156750f 73.375670f 865 4800 212350 D-18T 229.856755f 3200"},
                {"C130 72 180.50f"},
                {"А380 525 70.50f 72.75f 79.75f 1020                                               5250 25552 GP7270 75.00f 15400"}
        });
    }

    @Test
    public void check() {
        Assert.assertFalse(DataChecker.checkPlane(planeInfo));
    }
}
