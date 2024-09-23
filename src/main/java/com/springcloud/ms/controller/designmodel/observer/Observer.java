package com.springcloud.ms.controller.designmodel.observer;

public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}