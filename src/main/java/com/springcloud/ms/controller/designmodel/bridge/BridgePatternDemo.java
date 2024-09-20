package com.springcloud.ms.controller.designmodel.bridge;

/**
 *
 * 输出：
 * Drawing Circle[ color: red, radius: 10, x: 100, 100]
 * Drawing Circle[  color: green, radius: 10, x: 100, 100]
 */
public class BridgePatternDemo {
   public static void main(String[] args) {
      Shape redCircle = new Circle(100,100, 10, new RedCircle());
      Shape greenCircle = new Circle(100,100, 10, new GreenCircle());
 
      redCircle.draw();
      greenCircle.draw();
   }
}