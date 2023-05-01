package com.github.jabinespbi.autoplay;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeoutException;

public class InputControl {
    private final static long ONE_SECOND = 1_000;

    public InputControl() {

    }

    /**
     * Wait the color to be detected at the coordinate.
     *
     * @param x
     * @param y
     * @param red
     * @param green
     * @param blue
     */
    public static void wait(long ms,
                            int x, int y,
                            int red, int green, int blue) throws TimeoutException {
        waitForMultipleColor(ms, x, y, java.util.List.of(new Color(red, green, blue)));
    }


    /**
     * Wait for any color in the list to be detected at the coordinate.
     *
     * @param ms
     * @param x
     * @param y
     * @param colors
     * @throws TimeoutException
     */
    public static void waitForMultipleColor(long ms,
                                            int x, int y,
                                            java.util.List<Color> colors) throws TimeoutException {
        Robot robot = RobotContainer.getInstance()
                .getRobot();

        long start = System.currentTimeMillis();
        while (true) {
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > ms) {
                System.out.println(elapsed);
                break;
            }

            Color color = robot.getPixelColor(x, y);

            for (Color colorCompare : colors) {
                if (color.getRed() == colorCompare.getRed() &&
                        color.getGreen() == colorCompare.getGreen() &&
                        color.getBlue() == colorCompare.getBlue()) {
                    System.out.println("Correct target!!!");
                    return;
                }
            }

            System.out.printf("Actual %d %d %d\n", color.getRed(), color.getGreen(), color.getBlue());

            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        throw new TimeoutException();
    }

    public static void click(int x, int y) {
        Robot robot = RobotContainer.getInstance()
                .getRobot();

        robot.mouseMove(x, y);
        robot.delay(200);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(200);
    }

    public static void holdKey(int ms, int key) {
        Robot robot = RobotContainer.getInstance()
                .getRobot();

        robot.keyPress(key);
        robot.delay(ms);
        robot.keyRelease(key);
        robot.delay(200);
    }
}
