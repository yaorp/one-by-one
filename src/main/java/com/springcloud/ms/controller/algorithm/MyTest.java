package com.springcloud.ms.controller.algorithm;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * 测试类
 * @author: yaorp
 */
public class MyTest {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i=0;i<4; i++){
            a.add(i);
        }
        System.out.println(JSONObject.toJSONString(a));

    }
}
