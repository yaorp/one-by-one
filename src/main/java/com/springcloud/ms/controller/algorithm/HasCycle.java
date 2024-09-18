package com.springcloud.ms.controller.algorithm;

import com.springcloud.ms.controller.entity.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 * @author: yaorp
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 暴力法，遍历没有个数据，然后存在hash表里面，如果存在，表示是一个环
     */
    public static boolean hasCycle2(ListNode head){
        HashSet<Object> seen = new HashSet<>();
        while (head !=null){
            if (!seen.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针相差1，快指针每次移动两个，慢指针每次移动一个，他们相遇则为环，快指针为null，则不为环
     * @param head
     * @return
     */
    public static boolean hasCycle3(ListNode head){
        ListNode slow = head;
        ListNode quick = head.next;
        while (slow !=quick){
            if (quick ==null || quick.next==null){
                return false;
            }
            slow=slow.next;
            quick=quick.next.next;
        }

        return true;
    }

    public static void main(String[] args){

        ListNode nodeSta = new ListNode(0);    //创建首节点
        ListNode nextNode;                     //声明一个变量用来在移动过程中指向当前节点
        nextNode=nodeSta;                      //指向首节点

        //创建链表
        for(int i=1;i<10;i++){
            ListNode node = new ListNode(i);  //生成新的节点
            nextNode.next=node;               //把心节点连起来
            nextNode=nextNode.next;           //当前节点往后移动
        } //当for循环完成之后 nextNode指向最后一个节点，
//        nextNode.next=nodeSta;
        nextNode=nodeSta;                     //重新赋值让它指向首节点
//        print(nextNode);                      //打印输出
        System.out.println(hasCycle3(nextNode));

    }

    //打印输出方法
    static void print(ListNode listNoed){
        //创建链表节点
        while(listNoed!=null){
            System.out.println("节点:"+listNoed.val);
            listNoed=listNoed.next;
        }
        System.out.println();
    }
}
