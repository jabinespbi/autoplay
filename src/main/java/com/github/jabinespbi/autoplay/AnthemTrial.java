package com.github.jabinespbi.autoplay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.github.jabinespbi.autoplay.CurrentThread.sleep;

public class AnthemTrial {
        private static final long WAIT_FOR_LEADER_TO_START_MILLIS = 2 * 60 * 1_000; // 2 minutes
    private static final int WAIT_FOR_ANTHEM_TRIAL_TO_LOAD = 60 * 1_000; // 1 minute
    private static final long WAIT_FOR_ANTHEM_TRIAL_TO_FINISH = 10 * 60 * 1_000; // 10 minutes
    private static final int WAIT_FOR_ANTHEM_TRIAL_TO_EXIT = 10 * 1_000; // 5 seconds

    // after leaving, there is something wrong, it will not restart, just in the starting [position
    public void play() throws TimeoutException {

        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            // 1. Talk to Culifa, the player should stand at Culifa's position
            // Coordinates = java.awt.Point[x=-969,y=577]
            //Color = java.awt.Color[r=67,g=51,b=63]
            InputControl.click(-969, 577);

            clickAnthemTrialButton();
            // wait for anthem trial menu screen to appear
            // Color = java.awt.Color[r=231,g=52,b=41]
            //Coordinates = java.awt.Point[x=-1286,y=188]
            InputControl.waitForMultipleColor(5_000, 100,
                    -1286, 188,
                    java.util.List.of(
                            new Color(231, 52, 41),
                            new Color(29, 31, 42),
                            new Color(238, 242, 240)));
            clickPartyFinderButton();
            clickBecomeLeaderButton();
            clickAutoMatchButton();

            // Wait for a party
            // (if party is joined, the screen will go back to anthem trial menu screen,
            // we wait for the anthem trial menu screen color)
            // Color = java.awt.Color[r=231,g=52,b=41]
            //Coordinates = java.awt.Point[x=-1286,y=188]
            InputControl.waitForMultipleColor(10 * 60 * 1_000, 100,
                    -1286, 188,
                    java.util.List.of(
                            new Color(231, 52, 41)));

            // 5. Agree
            // Coord = java.awt.Point[x=-855,y=741]
            //Color = java.awt.Color[r=81,g=108,b=216]
            //81 108 216
            //82 109 215
            //

            try {
                InputControl.waitForMultipleColor(WAIT_FOR_LEADER_TO_START_MILLIS, 100,
                        -855, 741,
                        java.util.List.of(
                                new Color(81, 108, 216),
                                new Color(82, 109, 215)));
            } catch (TimeoutException e) {
                InputControl.click(-1788, 118); // click return button in anthem trial menu screen
                InputControl.click(-1828, 557); // click open party
                try {
                    InputControl.click(-152, 304); // click member tab
                    InputControl.wait(1_000, 100, -1595, 921, 89, 116, 216); // wait for a leave button to appear
                    InputControl.click(-1595, 921); // click leave button
                } catch (TimeoutException e2) {
                    //ignore
                }
                continue;
            }

            clickAgreeButton();

            // 6. Wait for the turn wheel button to appear
            //Color = java.awt.Color[r=228,g=239,b=250]
            //Coordinates = java.awt.Point[x=-670,y=645]
            InputControl.waitForMultipleColor(
                    WAIT_FOR_ANTHEM_TRIAL_TO_LOAD,
                    100,
                    -670, 645,
                    List.of(new Color(228, 239, 250))
            );

            // 7. Click and Wait for the turning of wheel to finish
            clickTheTurningWheelButton();

            // go to middle
            //Coordinates = java.awt.Point[x=-969,y=152]
            //Color = java.awt.Color[r=190,g=178,b=189]
            InputControl.holdKey(500, KeyEvent.VK_A);
            InputControl.holdKey(5_500, KeyEvent.VK_W);

            // 7. auto button
            // Coordinates = java.awt.Point[x=-224,y=309]
            //Color = java.awt.Color[r=245,g=248,b=246]
            autoAttackMonsters();

            // 8. tap anywhere
            //Coord = java.awt.Point[x=-991,y=403]
            //Color = java.awt.Color[r=222,g=107,b=16]
            //Actual 222 106 16
            InputControl.waitForMultipleColor(
                    WAIT_FOR_ANTHEM_TRIAL_TO_FINISH,
                    100,
                    -991, 403,
                    List.of(new Color(222, 106, 16))
            );
            InputControl.click(-991, 403);

            // 10. Open party
            // Coordinates = java.awt.Point[x=-1828,y=557]
            //Color = java.awt.Color[r=151,g=160,b=176]
            InputControl.click(-1828, 557);
            robot.delay(1_000);

            // Leave party
            //Coordinates = java.awt.Point[x=-1610,y=928]
            //Color = java.awt.Color[r=90,g=114,b=215]
            try {
                InputControl.wait(1_000, 100, -1595, 921, 89, 116, 216); // wait for a leave button to appear
                InputControl.click(-1595, 921); // click leave button
            } catch (TimeoutException e2) {
                System.out.println("There is no leave button that have appeared" + e2);
                //ignore
            }

            // Confirm
            // Coordinates = java.awt.Point[x=-816,y=713]
            //Color = java.awt.Color[r=86,g=113,b=205]
            InputControl.click(-816, 713);
            robot.delay(1_000);

            // 9. Wait for 5 seconds
            robot.delay(WAIT_FOR_ANTHEM_TRIAL_TO_EXIT);
        }
    }

    private void autoAttackMonsters() {
        // 1. click auto button
        InputControl.click(-224, 309);

        // 2. all monsters
        InputControl.click(-391, 428);

        // 3. close auto
        InputControl.click(-225, 371);
    }

    private void clickTheTurningWheelButton() {
        InputControl.click(-670, 645);
        sleep(10_500);
    }

    private void clickAgreeButton() {
        InputControl.click(-855, 741);
    }

    private void clickAutoMatchButton() {
        InputControl.click(-1554, 905);
    }

    private void clickBecomeLeaderButton() {
        InputControl.click(-1340, 924);
    }

    private void clickPartyFinderButton() {
        InputControl.click(-624, 882);
    }

    private void clickAnthemTrialButton() {
        InputControl.click(-397, 649);
    }
}
