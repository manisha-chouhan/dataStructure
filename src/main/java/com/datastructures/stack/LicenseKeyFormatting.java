package com.datastructures.stack;

import java.util.Stack;

public class LicenseKeyFormatting {
        public String licenseKeyFormatting(String S, int K) {
            Stack<Character> stack = new Stack<>();
            for(char ch : S.toCharArray()) {
                if(ch == '-')continue;
                else if(Character.isDigit(ch))stack.push(ch);
                else stack.push(Character.toUpperCase(ch));
            }
            StringBuilder sb = new StringBuilder();
            int count = 0;
            while(!stack.isEmpty()) {
                if(count == K) {
                    count = 0;
                    sb.insert(0,'-');
                }
                sb.insert(0,stack.pop());
                count++;
            }
            return sb.toString();
        }
}
