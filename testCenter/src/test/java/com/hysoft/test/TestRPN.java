package com.hysoft.test;

import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by yulifan on 2017/8/5.
 */
public class TestRPN {
  private final String chars = "+-*/()";
  private final String decimalchars = "0123456789";
  private static final HashMap<String, Integer> charMap = new HashMap();
  List<String> ret = new ArrayList();

  static {
    charMap.put("(", 0);
    charMap.put(")", 0);
    charMap.put("+", 1);
    charMap.put("-", 1);
    charMap.put("*", 2);
    charMap.put("/", 2);
  }

  /**
   * 转换表达式为后缀表示法
   */

  @Test
  public void testTransferToSuffix() throws Exception {
    //String cc = "4 + 5*( 6 + (7 - 5) * 2 ) + 190";
    String cc = "4+5*6";
    Stack<String> stack = new Stack();
    String dec = "";
    for (int i = 0; i < cc.length(); i++) {
      String tmp = cc.substring(i, i + 1);
      if (decimalchars.indexOf(tmp) >= 0) {
        dec += tmp;
      } else {
        ret.add(dec);
        dec = "";
        if (")".equals(tmp)) {
          popBracket(stack);
        } else if ("(".equals(tmp)) {
          stack.push(tmp);
        } else if (chars.indexOf(tmp) >= 0) {
          if (stack.isEmpty()) {
            stack.push(tmp);
          } else {
            popPriority(stack, tmp);
          }
        }
      }
    }

    if (!"".equals(dec)) {
        ret.add(dec);
    }

    while (!stack.isEmpty()) {
      ret.add(stack.pop());
    }

    for(String ff : ret) {
      if("".equals(ff)) continue;
      Assert.hasText("cc");
      if("(".equals(ff)) {
        throw new Exception("a wrong exressions! left out a right bracket");
      };

      System.out.print(ff + " ");
    }
    System.out.println();
  }

  public void popBracket(Stack<String> stack) {
    boolean hasLeft = false;
    while (!stack.isEmpty()) {
      String tmp = stack.pop();
      if ("(".equals(tmp)) {
        hasLeft = true;
        break;
      } else {
        ret.add(tmp);
      }
    }

    Assert.isTrue(hasLeft, "it is a wrong expression,left out a left bracket");
  }

  public void popPriority(Stack<String> stack, String currChar) {
    while (!stack.isEmpty()) {
      String popStr = stack.lastElement();

      Integer currPri = charMap.get(currChar);
      Assert.notNull(currPri, "it is a wrong math expression!" + currChar);
      Integer popPri = charMap.get(popStr);
      Assert.notNull(popPri, "it is a wrong math expression!" + popStr);
      if (popPri >= currPri) {
        String tmp = stack.pop();
        ret.add(tmp);
      } else {
        break;
      }
    }

    stack.push(currChar);
  }

  @Test
  public void testCalculate() throws Exception {
    testTransferToSuffix();
    Stack<String> stack = new Stack();
    for (String tmp:ret) {
      if("".equals(tmp)) continue;
      if (chars.indexOf(tmp) != -1) {
        String d1 = stack.pop();
        String d2 = stack.pop();
        int result = 0;
        switch (tmp.toCharArray()[0]) {
          case '*':
            result = Integer.valueOf(d1).intValue() * Integer.valueOf(d2).intValue();
            break;
          case '/':
            result = Integer.valueOf(d2).intValue() / Integer.valueOf(d1).intValue();
            break;
          case '+':
            result = Integer.valueOf(d1).intValue() + Integer.valueOf(d2).intValue();
            break;
          case '-':
            result = Integer.valueOf(d2).intValue() - Integer.valueOf(d1).intValue();
            break;
          default:
            throw new Exception("a wrong exression");
        }

        stack.push(Integer.valueOf(result).toString());
      } else {
        stack.push(tmp);
      }
    }

    System.out.println(stack.pop());
  }
}
