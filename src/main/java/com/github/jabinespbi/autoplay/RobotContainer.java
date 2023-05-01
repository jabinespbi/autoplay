package com.github.jabinespbi.autoplay;

import java.awt.*;

public class RobotContainer {
    private static final RobotContainer robotContainer = new RobotContainer();
    private final Robot robot;

    private RobotContainer() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static RobotContainer getInstance() {
        return robotContainer;
    }

    public Robot getRobot() {
        return robot;
    }
}
