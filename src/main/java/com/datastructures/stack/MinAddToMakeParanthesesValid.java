package com.datastructures.stack;

import java.util.Stack;

public class MinAddToMakeParanthesesValid {
    public int minAddToMakeValid(String S) {
        int count = 0;
        Stack<Character> stack = new Stack<>();

        for(char ch : S.toCharArray()) {
            if(ch == '(') stack.push(ch);
            else {
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else count++;
            }
        }
        return stack.size() + count;
    }
}
