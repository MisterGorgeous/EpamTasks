import com.slabadniak.task2.algoritm.MergeSort;
import com.slabadniak.task2.algoritm.QuickSort;
import com.slabadniak.task2.algoritm.ShellSort;
import com.slabadniak.task2.comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.task2.plane.Airliner;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.plane.SkyTruck;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAlgoritmCheck {
    @Mock
    Plane plane1 = Mockito.mock(Airliner.class);

    @Mock
    Plane plane2 = Mockito.mock(SkyTruck.class);

    @Mock
    Plane plane3 = Mockito.mock(Airliner.class);

    @Spy
    ArrayList<Plane> planes = Mockito.spy(ArrayList.class);

    @Test
    public void quickSortCheck(){
        when(plane1.getRangeOfFlight()).thenReturn(111);
        when(plane3.getRangeOfFlight()).thenReturn(222);
        when(plane3.getRangeOfFlight()).thenReturn(333);

        planes.add(plane3);
        planes.add(plane1);
        planes.add(plane2);

        ArrayList<Plane> collectSortPlanes = new ArrayList<>(planes);

        Collections.sort(collectSortPlanes,new PlaneRangeOfFlyComparator());
        QuickSort.sort(planes,new PlaneRangeOfFlyComparator());

        Assert.assertArrayEquals(planes.toArray(),collectSortPlanes.toArray());
    }

    @Test
    public void mergeSortCheck(){
        when(plane1.getRangeOfFlight()).thenReturn(111);
        when(plane3.getRangeOfFlight()).thenReturn(222);
        when(plane3.getRangeOfFlight()).thenReturn(333);

        planes.add(plane3);
        planes.add(plane1);
        planes.add(plane2);

        ArrayList<Plane> collectSortPlanes = new ArrayList<>(planes);

        Collections.sort(collectSortPlanes,new PlaneRangeOfFlyComparator());
        MergeSort.sort(planes,new PlaneRangeOfFlyComparator());

        Assert.assertArrayEquals(planes.toArray(),collectSortPlanes.toArray());
    }

    @Test
    public void shellSortCheck(){
        when(plane1.getRangeOfFlight()).thenReturn(111);
        when(plane3.getRangeOfFlight()).thenReturn(222);
        when(plane3.getRangeOfFlight()).thenReturn(333);

        planes.add(plane3);
        planes.add(plane1);
        planes.add(plane2);

        ArrayList<Plane> collectSortPlanes = new ArrayList<>(planes);

        Collections.sort(collectSortPlanes,new PlaneRangeOfFlyComparator());
        ShellSort.sort(planes,new PlaneRangeOfFlyComparator());

        Assert.assertArrayEquals(planes.toArray(),collectSortPlanes.toArray());
    }
}
