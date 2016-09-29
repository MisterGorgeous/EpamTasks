import com.slabadniak.Task1.Exeption.NotATriangleExeption;
import com.slabadniak.Task1.Point.Point;
import com.slabadniak.Task1.Triangle.Triangle;
import org.junit.Test;


public class NotATriangleCheck {
    @Test(expected = NotATriangleExeption.class)
    public void isTriangleValid() throws NotATriangleExeption {
        Triangle triangle = new Triangle(new Point(0,0),new Point(0,0),new Point(0,0));
    }
}
