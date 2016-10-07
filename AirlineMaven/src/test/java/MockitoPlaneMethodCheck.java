import com.slabadniak.task2.exeption.IncorrectDataExeption;
import com.slabadniak.task2.plane.Airliner;
import com.slabadniak.task2.plane.SkyTruck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class MockitoPlaneMethodCheck {

    @Mock
    SkyTruck skytruck = Mockito.mock(SkyTruck.class);

    @Mock
    Airliner airliner = Mockito.mock(Airliner.class);

    @Test(expected = IncorrectDataExeption.class)
    public void skyTruckTest() throws IncorrectDataExeption {
        skytruck.loadCargo(-20.00f);
    }

    @Test(expected = IncorrectDataExeption.class)
    public void airlinerTest() throws IncorrectDataExeption {
        airliner.loadPeople(-20);
    }

    @Test
    public void overrideTest(){
        try {
            Mockito.when(airliner.fly(-1)).thenReturn(-1.00f);
            org.junit.Assert.assertEquals(airliner.fly(-1), -1, 0.005);
        } catch (IncorrectDataExeption incorrectDataExeption) {
            incorrectDataExeption.printStackTrace();
        }

    }


}
