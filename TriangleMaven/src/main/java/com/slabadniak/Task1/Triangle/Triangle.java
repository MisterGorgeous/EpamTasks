package com.slabadniak.Task1.Triangle;

import com.slabadniak.Task1.Exeption.NotATriangleExeption;
import com.slabadniak.Task1.Point.Point;
import com.slabadniak.Task1.Num.PointNum;

public class Triangle {
    private Point firstPoint;
    private Point seccondPoint;
    private Point thirdPoint;

    public Triangle(Point firstPoint, Point seccondPoint, Point thirdPoint){
        this.firstPoint = firstPoint;
        this.seccondPoint = seccondPoint;
        this.thirdPoint = thirdPoint;
    }

    public void setPoints(Point firstPoint, Point seccondPoint, Point thirdPoint) throws NotATriangleExeption{
        this.firstPoint = firstPoint;
        this.seccondPoint = seccondPoint;
        this.thirdPoint = thirdPoint;
        checkTringle();
    }

    public Point getPoint(PointNum num){
        if(num == PointNum.FIRST) {
            return firstPoint;
        } else if( num == PointNum.SECCOND) {
            return seccondPoint;
        } else {
            return thirdPoint;
        }
    }

    public void checkTringle() throws NotATriangleExeption{
        if(firstPoint.equals(seccondPoint) || firstPoint.equals(thirdPoint) || seccondPoint.equals(thirdPoint)) {
            throw new NotATriangleExeption("Points are equal");
        }
    }

    public double calculatePerimetr() throws NotATriangleExeption {
        checkTringle();
        double sideA = calculateSide(firstPoint, seccondPoint);
        double sideB = calculateSide(firstPoint, thirdPoint);
        double sideC = calculateSide(seccondPoint, thirdPoint);
        return sideA + sideB + sideC;
    }

    public double calculateArea() throws NotATriangleExeption {
        double p = calculatePerimetr() / 2;
        return Math.sqrt(p * (p - calculateSide(firstPoint, seccondPoint)) * (p - calculateSide(firstPoint, thirdPoint))
                            * (p - calculateSide(seccondPoint, thirdPoint)));
    }

    private double calculateSide(Point first, Point seccond) {
        return Math.hypot(first.getX() - seccond.getX(),first.getY() - seccond.getY());
    }

    @Override
    public String toString() {
        return "(" + firstPoint.toString() + "), " + "(" + seccondPoint.toString() + "), " + "(" + thirdPoint.toString() + ") ";
    }
}
