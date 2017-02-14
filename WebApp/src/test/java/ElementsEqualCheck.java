import com.slabadniak.web.util.ActorValidation;
import org.junit.Assert;
import org.junit.Test;

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
