package com.datastructures.dp;

public class Medium {

    //https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        String output = s.charAt(0) + "";
        int max = 1;
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if(len == 2) {
                    if(s.charAt(i) == s.charAt(j)) dp[i][j] = true;
                }
                else {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    else dp[i][j] = false;

                }
                if(dp[i][j]) {
                    if(max < len) {
                        max = len;
                        output = s.substring(i, j + 1);
                    }
                }
            }
        }
        return output;
    }
}
