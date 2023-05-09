package com.github.jabinespbi.autoplay;

import java.util.concurrent.TimeoutException;

import static com.github.jabinespbi.autoplay.CurrentThread.sleep;

public class Foraging {
    public void play() {
        while (true) {
            //Coordinates = java.awt.Point[x=-670,y=635]
            //Color = java.awt.Color[r=113,g=171,b=219]
            InputControl.click(-670, 635);
            try {
                // Coordinates = java.awt.Point[x=-670,y=635]
                //Color = java.awt.Color[r=113,g=171,b=219]
                InputControl.wait(60_000, 100, -670, 635, 113, 171, 219);
            } catch (TimeoutException e) {
            }
            sleep(200);
        }
    }
}
