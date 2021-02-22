package com.datastructures.math;

import java.util.Stack;

public class Easy {

    //https://leetcode.com/problems/reverse-integer/
    public int reverse(int x) {
        int isSign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        Stack<Integer> stack = new Stack<>();

        while(x > 0) {
            stack.push(x % 10);
            x = x/10;
        }
        int num = 0;
        int i = 0;
        while(!stack.isEmpty()) {
            num += stack.pop() * Math.pow(10, i++);
            if(num >= Integer.MAX_VALUE) return 0;
        }
        return isSign * num;
    }

    //https://leetcode.com/problems/decode-ways/
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i <= s.length(); i++) {
            char singleDigit = s.charAt(i - 1);
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if(singleDigit != '0') {
                dp[i] += dp[i - 1];
            }
            if(twoDigits > 9 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    //https://leetcode.com/problems/maximum-69-number/
    public int maximum69Number (int num) {
        int max = num;
        String s = num + "";
        for(int i = 0; i < s.length(); i++) {
            String temp = s.substring(0, i) + (s.charAt(i) == '6' ? "9" : "6")+ s.substring(i + 1);
            int _temp = Integer.parseInt(temp);
            max = Math.max(max, _temp);
        }
        return max;
    }
}
