package com.leasehouse;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yulifan on 2017/7/22.
 */
public class TestCountDownLatch {
    static CountDownLatch downLatch  = new CountDownLatch(2);

    public static void main(String[] args) {
        Thread thread =  new Thread(
          new Runnable() {
            public void run() {
                downLatch.countDown();
                System.out.println("1");
                downLatch.countDown();
                System.out.println("2");
            }
          }
        );

         thread.start();

         try {
             downLatch.await();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
      System.out.println("main is end");
    }
}
