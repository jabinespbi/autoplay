package com.github.jabinespbi.autoplay;

import static com.github.jabinespbi.autoplay.CurrentThread.loopUntilWithSleep;

public class AutoClicker {
    public void play() {
        loopUntilWithSleep(
                () -> InputControl.clickWithLessDelay(-439, 966),
                10_000,
                0);
    }
}
