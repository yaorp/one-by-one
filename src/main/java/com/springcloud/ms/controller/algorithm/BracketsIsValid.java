package com.springcloud.ms.controller.algorithm;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
}
