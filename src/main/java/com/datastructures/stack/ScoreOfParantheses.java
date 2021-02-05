package com.datastructures.stack;

import java.util.Stack;

public class ScoreOfParantheses {
    public int scoreOfParentheses(String S) {
        Stack<String> stack = new Stack<>();
        int count = 0;
        for(char ch : S.toCharArray()) {
            if(stack.isEmpty() || ch == '(')stack.push(ch + "");
            if(ch == ')') {
                int num = 0;
                while(!stack.isEmpty() && !stack.peek().equals("(")) {
                    num = num + Integer.parseInt(stack.pop());
                }
                stack.pop();
                if(num != 0) {
                    num = 2 * num;
                    stack.push(num + "");
                }
                else {
                    stack.push("" + 1);
                }
            }
        }
        while(!stack.isEmpty()) {
            count += Integer.parseInt(stack.pop());
        }
        return count;
    }
}
