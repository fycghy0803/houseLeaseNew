package com.leasehouse;

import java.util.concurrent.*;

public class TestCallable {
    public static void main(String[] args) {
       ExecutorService executorService = Executors.newFixedThreadPool(10);

       try {
         executorService.execute(new Runnable() {
           public void run() {
             System.out.println("ccc");
           }
         });
          Future<User1> future = executorService.submit(new taskCallable());
          User1 ff = future.get();
          System.out.println("ff = " + ff.getName());

       } catch (InterruptedException e) {
          e.printStackTrace();
       } catch (ExecutionException e) {
          e.printStackTrace();
       } finally {
          executorService.shutdown();
       }
    }
}

class taskCallable implements Callable<User1> {
  public User1 call() throws Exception {
        String cc = "fyc";
        System.out.println("cc =" + cc);
        User1 user1 = new User1();
        user1.setName(cc);
        Thread.sleep(3000);
        return user1;
   }
}


class User1 {
  private String name;
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
