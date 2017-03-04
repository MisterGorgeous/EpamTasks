import com.slabadniak.web.util.ActorValidation;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test is used to check @see checkSizes and @see checkForNull methods.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class ElementsEqualCheck {

    @Test
    public void isEqual() {
        Assert.assertEquals(ActorValidation.checkSizes(3,4,3,3),true);
    }

    @Test
    public void isNull() {
        String[] array = new String[1];
        Assert.assertEquals(ActorValidation.checkForNull(array,array,null,array),false);
    }
}
