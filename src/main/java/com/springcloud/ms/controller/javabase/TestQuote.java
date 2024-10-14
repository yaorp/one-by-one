package com.springcloud.ms.controller.javabase;

import java.util.ArrayList;

/**
 * @author: yaorp
 */
//注释
/*注释*/
/**注释*/
/**注释**/
public class TestQuote {
    static void make(ArrayList al){
        al.add(3);
        al= new ArrayList();
        al.add(0);
        al.add(10);
        al.remove(1);
    }
    public static void main(String[] args){
        ArrayList al=new ArrayList();
        al.add(2);
        make(al);
        System.out.println(al.get(1));
    }
}
