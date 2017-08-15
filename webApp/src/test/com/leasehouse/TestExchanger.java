package com.leasehouse;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<String>();
    static ExecutorService executorPool = Executors.newFixedThreadPool(2);

    public static  void main(String[] args) {
        executorPool.execute(new Runnable() {
          public void run() {
              String a = "A";
            try {
              String b = exchanger.exchange(a);
              System.out.println("b =" + b + ";a=" + a);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        executorPool.execute(new Runnable() {
          public void run() {
             String b = "B";
            try {
                String a = exchanger.exchange(b);
                System.out.println("b =" + b + ";a=" + a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

          }
        });

        executorPool.shutdown();
    }
}
