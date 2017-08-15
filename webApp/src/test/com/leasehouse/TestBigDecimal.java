package com.leasehouse;

import java.math.BigDecimal;

/**
 * Created by yulifan on 2017/7/23.
 */
public class TestBigDecimal {
  static  BigDecimal bigDecimal;

  public BigDecimal getBigDecimal() {
    return bigDecimal;
  }

  public void setBigDecimal(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

  public static  void main(String[] args) {
    System.out.println(bigDecimal.add(new BigDecimal(1)));
  }
}
