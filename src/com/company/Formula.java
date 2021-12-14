package com.company;

public class Formula {
    public static double probability(double f1, double f2, double temp) {
        if (f2 < f1) return 1;
        return Math.exp((f1 - f2) / temp);
    }

    public static double distance(Section section1, Section section2) {
        int xDist = Math.abs(section1.getX() - section2.getX());
        int yDist = Math.abs(section1.getY() - section2.getY());
        return Math.sqrt(xDist * xDist + yDist * yDist);
    }
}
