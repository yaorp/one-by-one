package com.springcloud.ms.controller.algorithm;

/**
 * @author: yaorp
 */
public class ReverseLinkedListTest {
    static class Node {
        int val;
        Node next;
        Node(int x, Node nextNode) {
            val = x;
            next = nextNode;
        }
    }

    public static Node recursionNode(Node head, Node prev) {
        if (null == head.next) {
            head.next = prev;
            return head;
        }
        Node tempNext = head.next;
        head.next = prev;
        prev = head;
        head = tempNext;
        Node result = recursionNode(head, prev);
        return result;
    }

    public static void main(String[] args) {
        Node n4 = new Node(4, null);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);
        Node head = n1;
        head = recursionNode(head, null);
        System.out.println(n4);
    }
}
