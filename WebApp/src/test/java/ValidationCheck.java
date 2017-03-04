import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.util.UserValidation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


/**
 * This parameterized test are using to test inner validation.
 * @author Slabadniak Sergei
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class ValidationCheck {
    private String text;

    public ValidationCheck(String text) {
        this.text = text;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> controlTraingles() {
        return Arrays.asList(new Object[][]{
                {"Sergei2401"},
                {"2401"},
                {"slabadniak@gmail.com"},
                {"Epam2017"},
                {"@gmail.com"},
                {"login"},
                {"login1742"}

        });
    }

    @Test
    public void checkLogin() {
        Feedback feedback = UserValidation.checkLogin(text,"en_US");
        Assert.assertEquals(feedback.isWritten(),false);
    }

    @Test
    public void checkPassword() {
        Feedback feedback = UserValidation.checkPassword(text,"en_US");
        Assert.assertEquals(feedback.isWritten(),false);
    }

    @Test
    public void checkEmail() {
        Feedback feedback = UserValidation.checkEmail(text,"en_US");
        Assert.assertEquals(feedback.isWritten(),false);
    }
}
