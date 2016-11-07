import com.slabadniak.task4.interpreter.TerminalExpression;
import com.slabadniak.task4.notation.PolishNotation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NotationCheck {
    private String expression;
    private String result;

    public NotationCheck(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> controlTraingles() {
        return Arrays.asList(new Object[][]{
                {"(5-6)*7", "-7"},
                {"(5-6)*(--7)", "-6"},
                {"(5-6)*7++", "-7"},
                {"(5---6)*7", "-7"},
                {"(5-(++6))*7", "-14"},
                {"(5++-6)*7", "-7"},
                {"(++5-6)*7", "0"}
        });
    }

    @Test
    public void checkNotation() {
        PolishNotation notation = new PolishNotation();
        notation.make(expression);
        new TerminalExpression().interpreting(notation);
        String result = notation.getResult();
        Assert.assertEquals(result, this.result);
    }
}
