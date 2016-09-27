package com.slabadniak.Task1.Test;

import com.slabadniak.Task1.Exeption.NotATriangleExeption;
import com.slabadniak.Task1.Num.PointNum;
import com.slabadniak.Task1.Triangle.Triangle;
import com.slabadniak.Task1.Point.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TriangleCheck {
    private Point first;
    private Point seccond;
    private Point third;

    public TriangleCheck(Point first, Point seccond, Point third) {
        this.first = first;
        this.seccond = seccond;
        this.third = third;
    }

    @Parameters
    public static Collection<Object[]> controlTraingles() {
        return Arrays.asList(new Object[][] {
                { new Point(12,1), new Point(1,13),new Point(15,16) },
                { new Point(9,0), new Point(0,0),new Point(0,5)},
                {new Point(0,0), new Point(1,1),new Point(2,2) }
        });
    }

    @Test
        public void isPointsFormTriangle(){
            Triangle triangle = new Triangle();
        try {
            triangle.setPoints(first,seccond,third);
        } catch (NotATriangleExeption notATriangleExeption) {
            notATriangleExeption.printStackTrace();
        }
        Assert.assertNotEquals("Three points in one line!!!",(triangle.getPoint(PointNum.FIRST).getY() - triangle.getPoint(PointNum.SECCOND).getY()) * triangle.getPoint(PointNum.THIRD).getX() +
                                                            (triangle.getPoint(PointNum.SECCOND).getX() - triangle.getPoint(PointNum.FIRST).getX()) * triangle.getPoint(PointNum.THIRD).getY() +
                                                             (triangle.getPoint(PointNum.FIRST).getX() * triangle.getPoint(PointNum.SECCOND).getY() - triangle.getPoint(PointNum.FIRST).getY() *
                                                                     triangle.getPoint(PointNum.SECCOND).getX()), 0, Point.ACCEPTABLE_DELTA);
    }

    @Test
        public void isRectangularTriangle(){
        Triangle triangle = new Triangle();
        try {
            triangle.setPoints(first,seccond,third);
        } catch (NotATriangleExeption notATriangleExeption) {
            notATriangleExeption.printStackTrace();
        }
            double a1 = triangle.getPoint(PointNum.FIRST).getX() - triangle.getPoint(PointNum.SECCOND).getX();
            double a2 = triangle.getPoint(PointNum.SECCOND).getX() - triangle.getPoint(PointNum.THIRD).getX();
            double a3 = triangle.getPoint(PointNum.FIRST).getX() - triangle.getPoint(PointNum.THIRD).getX();
            double b1 = triangle.getPoint(PointNum.FIRST).getY() - triangle.getPoint(PointNum.SECCOND).getY();
            double b2 = triangle.getPoint(PointNum.SECCOND).getY() - triangle.getPoint(PointNum.THIRD).getY();
            double b3 = triangle.getPoint(PointNum.FIRST).getY() - triangle.getPoint(PointNum.THIRD).getY();
            Assert.assertFalse("One of the angles equals 90 degrees", checkAngle(a1, a2, b1, b2));
            Assert.assertFalse("One of the angles equals 90 degrees", checkAngle(a1, a3, b1, b3));
            Assert.assertFalse("One of the angles equals 90 degrees", checkAngle(a3, a2, b3, b2));
    }

    private boolean checkAngle(double a1, double a2, double b1, double b2){
        double cos = (a1 * a2 + b1 * b2) / (( Math.hypot(a1, b1) * Math.hypot(a2, b2)));
        return cos == 0;
    }
}