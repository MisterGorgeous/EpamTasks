import com.slabadniak.task5.Builder;
import com.slabadniak.task5.entity.Journey;
import com.slabadniak.task5.parser.DOMParser;
import com.slabadniak.task5.parser.SAXParser;
import com.slabadniak.task5.parser.StAXParser;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class CheckParsers {
        @Test
        public void isResult(){
            ArrayList<Journey> result = new ArrayList<>();
            Builder builder = new Builder();

            result = builder.create(new DOMParser());
            Assert.assertFalse(result.isEmpty());
            result = builder.create(new SAXParser());
            Assert.assertFalse(result.isEmpty());
            result = builder.create(new StAXParser());
            Assert.assertFalse(result.isEmpty());
        }
}
