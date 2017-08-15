package com.leasehouse;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yulifan on 2017/8/1.
 */
public class TestBuffer {

   public static void main(String[] args) {
     IntBuffer intBuffer = IntBuffer.allocate(8);
     intBuffer.put(1);

     try {
          //FileInputStream fileInputStream = new FileInputStream("");
          Path path = Paths.get("/Users/yulifan/Desktop/cc.txt");

          FileChannel fileChannel = FileChannel.open(path);
          ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
               int len = 0;
          while((len = fileChannel.read(byteBuffer)) != -1) {
             System.out.println(byteBuffer);
          }
     } catch (FileNotFoundException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }

   }
}
