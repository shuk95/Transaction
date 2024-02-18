package com.codes.shuk.Utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchUtils {
    public static void await(CountDownLatch countDownLatch){
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
    }
    public static void countDown(CountDownLatch countDownLatch){
        countDownLatch.countDown();
    }
}
