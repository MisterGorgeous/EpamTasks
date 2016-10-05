import com.slabadniak.task2.airline.EpamAirlines;
import com.slabadniak.task2.airport.airportlocation.AirportLocationCity;
import com.slabadniak.task2.exeption.InvalidArgumentExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.ticketclass.TicketClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class AirportSystemCheck {
    @Test(expected = InvalidArgumentExeption.class)
    public void availabilityOfPlane() throws InvalidArgumentExeption {
        EpamAirlines airline = EpamAirlines.getAirline();
        ArrayList<Plane> planes = airline.getAviation().getPlanes();
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            iterator.next().takeOff();
        }
        airline.getAiroports().flyFromTo(AirportLocationCity.AMSTERDAM,AirportLocationCity.BERLIN, TicketClass.ECONOMY);
    }
}
