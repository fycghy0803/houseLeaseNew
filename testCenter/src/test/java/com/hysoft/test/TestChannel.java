package com.hysoft.test;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yulifan on 2017/8/2.
 */
public class TestChannel {

   @Test
   public void testSelector() {
     Path path = Paths.get("/Users/yulifan/../cc.txt");
     System.out.println(path.normalize());
     System.out.println(Paths.get(".").toAbsolutePath().normalize());

     boolean fileExists = Files.exists(path);
     System.out.println("fileExists = " + fileExists);
   }
}
