package com.github.jabinespbi.autoplay;

import java.util.concurrent.TimeoutException;

import static com.github.jabinespbi.autoplay.CurrentThread.sleep;

public class Fishing {
    public void play() {
        while (true) {
            //Coordinates = java.awt.Point[x=-946,y=362]
            //Color = java.awt.Color[195 183 175]
            InputControl.click(-946, 362);
            try {
                // Coordinates = java.awt.Point[x=-946,y=284]
                //Color = java.awt.Color[254 238 238]
                InputControl.wait(60_000, 100, -946, 284, 254, 238, 238);
            } catch (TimeoutException e) {
            }

            InputControl.click(-946, 362);
            sleep(3_500);
        }
    }
}
