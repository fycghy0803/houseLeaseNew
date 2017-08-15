package com.leasehouse;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class TestSemaphore {
    static Semaphore semaphore = new Semaphore(10);
    static ExecutorService executorPool = Executors.newFixedThreadPool(30);
    public static void main(String[] args) {
       for(int i = 0;i< 30;i++) {
           executorPool.execute(new Runnable() {
             public void run() {
               try {
                 semaphore.acquire();
                 System.out.println("save data...");
                 semaphore.release();
               } catch (Exception e) {
                 e.printStackTrace();
               }
             }
           });
       }

       ReentrantLock reentrantLock = new ReentrantLock();
       reentrantLock.lock();
       reentrantLock.unlock();

       executorPool.shutdown();
    }
}
