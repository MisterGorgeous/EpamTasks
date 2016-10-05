import com.slabadniak.task2.airline.EpamAirlines;
import com.slabadniak.task2.airport.AirportLocationCity;
import com.slabadniak.task2.exeption.UncorrectDataExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.airline.TicketClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class AirportSystemCheck {
    @Test(expected = UncorrectDataExeption.class)
    public void availabilityOfPlane() throws UncorrectDataExeption {
        EpamAirlines airline = EpamAirlines.getAirline();
        ArrayList<Plane> planes = airline.getAviation().getPlanes();
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            iterator.next().takeOff();
        }
        airline.getAiroports().flyFromTo(AirportLocationCity.AMSTERDAM,AirportLocationCity.BERLIN, TicketClass.ECONOMY);
    }
}
