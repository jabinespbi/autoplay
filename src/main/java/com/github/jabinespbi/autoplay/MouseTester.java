package com.github.jabinespbi.autoplay;

import java.awt.*;

public class MouseTester {
    public void play() throws AWTException {
        Robot robot = new Robot();
        robot.delay(2000);

        while (true) {
            Point coordinates = MouseInfo.getPointerInfo().getLocation();
            System.out.println("Coordinates = " + coordinates);

            Color color = robot.getPixelColor((int) coordinates.getX(), (int) coordinates.getY());
            System.out.println("Color = " + color);

            robot.delay(1_000);
        }
    }
}
