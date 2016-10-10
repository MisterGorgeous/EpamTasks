package com.slabadniak.Task1.Point;

public class Point {
    public static final double ACCEPTABLE_DELTA = 0.0001;
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return ((Math.abs(x - other.x) < ACCEPTABLE_DELTA) && (Math.abs(y - other.y) < ACCEPTABLE_DELTA));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) x;
        result = 31 * result + (int) y;
        return result;
    }

    @Override
    public String toString() {
        return "x =" + String.valueOf(x) + "; y =" + String.valueOf(y);
    }
}
