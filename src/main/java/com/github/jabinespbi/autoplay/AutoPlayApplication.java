package com.github.jabinespbi.autoplay;

import java.awt.*;

/**
 * Hello world!
 */
public class AutoPlayApplication {
    public static void main(String[] args) throws AWTException {
        PointerInfo pointer = MouseInfo.getPointerInfo();
        Point coord = pointer.getLocation();
        Robot robot = new Robot();
        robot.delay(2000);

        //print coords
        coord = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Coord = " + coord);

        //print colors
        Color color = robot.getPixelColor((int) coord.getX(), (int) coord.getY());
        System.out.println("Color = " + color);
    }
}
