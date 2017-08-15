package com.leasehouse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.AccessControlException;

/**
 * Created by yulifan on 2017/7/26.
 */
public class TestForkJoin {
    public static void main(String[] args) {
        System.setSecurityManager(new MySecurityManager());
        File file = new File("/Users/yulifan/Desktop/ff.exe");
      try {
          FileInputStream fileInputStream = new FileInputStream(file);
          byte[] cc = new byte[1024];
          //fileInputStream.read(cc);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
}

class MySecurityManager extends SecurityManager {
  @Override
  public void checkRead(String file){
       if(file.endsWith("exe")) {
           throw new AccessControlException("cannot read file:" + file);
       }
       super.checkRead(file);
  }
}
