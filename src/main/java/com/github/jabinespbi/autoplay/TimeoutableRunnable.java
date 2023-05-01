package com.github.jabinespbi.autoplay;

import java.util.concurrent.TimeoutException;

@FunctionalInterface
public interface TimeoutableRunnable {
    void run() throws TimeoutException;
}
