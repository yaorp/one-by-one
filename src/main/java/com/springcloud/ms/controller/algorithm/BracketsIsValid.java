package com.springcloud.ms.controller.algorithm;

import java.util.*;

/**
 * 20。有效的括号
 * <a href="https://leetcode.cn/problems/valid-parentheses/description/">...</a>
 * @author: yaorp
 */
public class BracketsIsValid {

    // 用栈来比较，匹配就出栈，否则就是false
    public static boolean isValid(String a){
        if (a.length() %2==1){
            return false;
        }

        Map<Character,Character> b = new HashMap<>();
        b.put(')','(');
        b.put(']','[');
        b.put('}','{');

        Deque<Character> deque = new LinkedList<>();
        for (int i=0; i<a.length();i++){
            char c = a.charAt(i);
            if (b.containsKey(c)){
                if (deque.isEmpty() || !b.get(c).equals(deque.peek()) ){
                    return false;
                }else {
                    deque.poll();
                }
            }else {
                deque.push(c);
            }

        }
        return deque.isEmpty();
    }

    /**
     * 栈入左括号相反的值，如果发现右括号字符，从栈中删除并判断
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c =='('){
                stack.push(')');
            } else if (c=='[') {
                stack.push(']');
            } else if (c=='{') {
                stack.push('}');
            }else if (!stack.isEmpty() && !stack.pop().equals(c)){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String a = "([])";
        System.out.println(isValid2(a));
    }
}
