import com.slabadniak.task4.regular.Regular;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegexCheck {
    private String data;

    public RegexCheck(String data) {
        this.data = data;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> controlTraingles() {
        return Arrays.asList(new Object[][]{
                {"word"},
                {"expression"},
                {"(23*4+34)/2"},
                {"++6-3"},
                {"'56-433',"},
                {"'lexeme'."}
        });
    }

    @Test
    public void checkWord() {
        Assert.assertNotEquals(Regular.getMatches(data, Regular.WORD).size(), 0);
    }

    @Test
    public void checkExpression() {
        Assert.assertNotEquals(Regular.getMatches(data, Regular.EXPRESION).size(), 0);
    }

    @Test
    public void checkLexeme() {
        Assert.assertNotEquals(Regular.getMatches(data, Regular.LEXEME).size(), 0);
    }
}
