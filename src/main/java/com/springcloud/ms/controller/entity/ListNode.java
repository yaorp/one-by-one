package com.springcloud.ms.controller.entity;

import lombok.Data;

//@Data
//类名 ：Java类就是一种自定义的数据结构
public class ListNode {
    //数据 ：节点数据
    public int val;
    //对象 ：引用下一个节点对象。在Java中没有指针的概念，Java中的引用和C语言的指针类似
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}