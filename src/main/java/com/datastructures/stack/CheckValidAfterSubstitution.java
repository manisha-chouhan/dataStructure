package com.datastructures.stack;

import java.util.Stack;

public class CheckValidAfterSubstitution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()) {

            if(stack.isEmpty()) {
                if(ch != 'a') return false;
                stack.push(ch);
            }
            else {
                if(ch == stack.peek()) stack.push(ch);

                else {
                    if(ch == 'c') {
                        int i = 0;
                        char prev = ch;
                        while(!stack.isEmpty() && i < 2) {
                            char curr = stack.pop();
                            if(curr != prev - 1)return false;
                            prev = curr;
                            i++;
                        }
                    }
                    else stack.push(ch);
                }
            }
        }
        return stack.isEmpty();
    }
}
