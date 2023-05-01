package com.github.jabinespbi.autoplay;

import java.awt.*;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 */
public class AutoPlayApplication {
    public static void main(String[] args) throws TimeoutException, AWTException {
        AnthemTrial anthemTrial = new AnthemTrial();
        anthemTrial.play();
//        MouseTester mouseTester = new MouseTester();
//        mouseTester.play();
    }
}
