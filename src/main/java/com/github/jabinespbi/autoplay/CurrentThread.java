package com.github.jabinespbi.autoplay;

public class CurrentThread {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loopUntilWithSleep(Runnable runnable, long untilInMillis, long sleepInMillis) {
        long start = System.currentTimeMillis();
        while (true) {
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > untilInMillis) {
                System.out.println(elapsed);
                break;
            }

            runnable.run();
            sleep(sleepInMillis);
        }
    }
}
