package com.datastructures.stack;

import java.util.Stack;

public class Medium {

    //https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
    public boolean isValidSerialization(String preorder) {
        String[] arr = preorder.split(",");
        int node = 0;
        int n = arr.length;
        if(n == 0) return true;
        if(arr[0].charAt(0) != '#') node = 2;
        int i = 1;


        while(i < n) {
            if(node == 0) return false;
            if(arr[i].charAt(0) != '#') {
                node++;
            }
            else node--;
            i++;
        }
        return node == 0;
    }

    //https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        Stack<Integer> stack = new Stack<>();

        for(char ch : num.toCharArray()) {
            int temp = Character.getNumericValue(ch);
            if(stack.isEmpty() || k <= 0 || temp > stack.peek()) {
                stack.push(temp);
                continue;
            }

            while(!stack.isEmpty() && k > 0 && temp < stack.peek()) {
                k--;
                stack.pop();
            }
            stack.push(temp);
        }
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()) {
            if(k > 0){
                k--;
                stack.pop();
            }
            else sb.append(stack.pop());
        }

        sb.reverse();
        String s = sb.toString();
        int i = 0;
        for(; i < s.length(); i++) {
            if(s.charAt(i) != '0')break;
        }
        return (sb.length() == 0 || i == s.length()) ? "0" : s.substring(i);
    }
}
