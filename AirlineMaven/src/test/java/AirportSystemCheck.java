import com.slabadniak.task2.airline.EpamAirline;
import com.slabadniak.task2.exeption.IncorrectDataExeption;
import com.slabadniak.task2.plane.Plane;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class AirportSystemCheck {
    @Test(expected = IncorrectDataExeption.class)
    public void availabilityOfPlane() throws IncorrectDataExeption {
        EpamAirline airline = EpamAirline.getAirline();
        ArrayList<Plane> planes = airline.getPlanes();
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            iterator.next().takeOff();
        }
        throw new IncorrectDataExeption("");
      //TO DO
    }
}
