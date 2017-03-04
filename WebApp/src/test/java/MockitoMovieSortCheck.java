import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.SortMovies;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


/**
 * This mockito test is used to test sorting method.
 * @author Slabadniak Sergei
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoMovieSortCheck {
    private static final String INCREASE = "Start with lowest rate";
    private static final String DECREASE = "Start with highest rate";
    private static final String ALPHABETIC = "Alphabetic";

    @Mock
    private Movie first = Mockito.mock(Movie.class);

    @Mock
    private Movie seccond = Mockito.mock(Movie.class);

    @Mock
    private Movie third = Mockito.mock(Movie.class);

    @Spy
    ArrayList<Movie> wrongOrder = Mockito.spy(ArrayList.class);

    @Spy
    ArrayList<Movie> writeOrder = Mockito.spy(ArrayList.class);


       @Test
    public void alphabeticCheck() throws ServiceExeption {
        when(first.getTitle()).thenReturn("Apple");
        when(seccond.getTitle()).thenReturn("Banana");
        when(third.getTitle()).thenReturn("Orange");

        wrongOrder.add(third);
        wrongOrder.add(first);
        wrongOrder.add(seccond);

        writeOrder.add(first);
        writeOrder.add(seccond);
        writeOrder.add(third);


        SortMovies.sort(wrongOrder,ALPHABETIC,DECREASE);
        Assert.assertArrayEquals(wrongOrder.toArray(),writeOrder.toArray());
    }

    @Test
    public void highestCheck() throws ServiceExeption {
        when(first.getRating()).thenReturn(4.2f);
        when(seccond.getRating()).thenReturn(6.8f);
        when(third.getRating()).thenReturn(9.1f);

        wrongOrder.add(third);
        wrongOrder.add(first);
        wrongOrder.add(seccond);

        writeOrder.add(first);
        writeOrder.add(seccond);
        writeOrder.add(third);


        SortMovies.sort(wrongOrder,INCREASE,ALPHABETIC);
        Assert.assertArrayEquals(wrongOrder.toArray(),writeOrder.toArray());
    }

    @Test
    public void lowestCheck() throws ServiceExeption {
        when(first.getRating()).thenReturn(7.2f);
        when(seccond.getRating()).thenReturn(5.8f);
        when(third.getRating()).thenReturn(2.1f);

        wrongOrder.add(third);
        wrongOrder.add(first);
        wrongOrder.add(seccond);

        writeOrder.add(first);
        writeOrder.add(seccond);
        writeOrder.add(third);


        SortMovies.sort(wrongOrder,DECREASE,INCREASE);
        Assert.assertArrayEquals(wrongOrder.toArray(),writeOrder.toArray());
    }

}
