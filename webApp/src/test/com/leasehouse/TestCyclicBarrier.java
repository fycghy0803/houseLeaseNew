package com.leasehouse;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by yulifan on 2017/7/22.
 */
public class TestCyclicBarrier {
    static  CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
         public void run() {
           System.out.println("count thread is running....");
         }
    });
    public static void main(String[] args) {
        new Thread(new Runnable() {
          public void run() {
            try {
              System.out.println("inner thread is running....");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
              e.printStackTrace();
            } catch (BrokenBarrierException e) {
              e.printStackTrace();
            }
          }
        }).start();

      try {
        System.out.println("mainis continue.....");
          cyclicBarrier.await();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
}
