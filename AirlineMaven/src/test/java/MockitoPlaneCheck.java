import com.slabadniak.task2.engine.Engine;
import com.slabadniak.task2.exeption.IncorrectDataExeption;
import com.slabadniak.task2.plane.SkyTruck;
import com.slabadniak.task2.planefactory.SkyTruckFactory;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoPlaneCheck {

    @Mock
    Engine engine = Mockito.mock(Engine.class);

    @InjectMocks
    SkyTruck skyTruck = new SkyTruckFactory().createPlane("");

    @Test
    public void surrogate() {
        Mockito.doReturn(false).when(engine).isEngineWorking();
        skyTruck.takeOff();
        Mockito.verify(engine, Mockito.atLeastOnce()).startEngine();
        Assert.assertEquals(false, skyTruck.isPlaneFlying());
    }

    @Test(expected = IncorrectDataExeption.class)
    public void loadCargo() throws IncorrectDataExeption {
        Mockito.doThrow(new IncorrectDataExeption("")).when(engine).isEngineWorking();
        engine.startEngine();
    }

}
