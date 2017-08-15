package com.leasehouse;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by yulifan on 2017/7/22.
 */
public class User {
   private int age;
   private volatile  String name;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static  void main(String[] args) {
    User u1 = new User();
    u1.setName("a1");
    u1.setAge(1);
    AtomicReference<User> userAtomicReference = new AtomicReference<User>(u1);

    User u2 = new User();
    u2.setAge(2);
    u2.setName("b1");
    userAtomicReference.compareAndSet(u1, u2);
    User tmp = userAtomicReference.get();

    System.out.println(tmp.getAge());
    System.out.println(tmp.getName());

    //原子更新对象中的引用类型的值,该引用类型必须为volatile
    AtomicReferenceFieldUpdater<User,String>  userupdate=  AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");
    userupdate.compareAndSet(u2, u2.name, "ff");
    userupdate.weakCompareAndSet(u2, u2.name, "dd");
    System.out.println(u2.getName());

    AtomicMarkableReference<User> userAtomicMarkableReference = new AtomicMarkableReference<User>(u1,true);
    userAtomicMarkableReference.attemptMark(u1,false);
    userAtomicMarkableReference.compareAndSet(u1, u2, false, true);
    User tmp1 = userAtomicMarkableReference.get(new boolean[]{false});
    System.out.println("cc ==" + tmp1.getName());

    AtomicStampedReference<User> atomicStampedReference = new AtomicStampedReference<User>(u1,1);

  }
}
