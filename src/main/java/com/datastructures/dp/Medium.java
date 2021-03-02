package com.datastructures.dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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

    //https://leetcode.com/problems/maximal-square/
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = 0;
        int[][] board = new int[n][m];
        int max = Integer.MIN_VALUE;

        for(i = 0; i < n; i++) {
            if(matrix[i][j] == '1')board[i][j] = 1;
            max = Math.max(max, board[i][j]);
        }
        i = 0;

        for(j = 0; j < m; j++) {
            if(matrix[i][j] == '1')board[i][j] = 1;
            max = Math.max(max, board[i][j]);
        }

        for(i = 1; i < n; i++) {
            for(j = 1; j < m; j++) {
                if(matrix[i][j] == '1') {
                    board[i][j] = Math.min(board[i - 1][j], Math.min(board[i][j - 1], board[i - 1][j - 1])) + 1;
                    max = Math.max(max, board[i][j]);
                }
            }
        }
        return max * max;

    }

    //https://leetcode.com/problems/continuous-subarray-sum/
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(k == 0) {
                if(map.containsKey(sum) && i - map.get(sum) > 1) return true;
            }
            else {
                int rem = sum%k;
                if(map.containsKey(rem) && i - map.get(rem) > 1) return true;
                if(map.containsKey(sum) && i - map.get(sum) > 1) return true;
                map.putIfAbsent(rem, i);
            }
            map.putIfAbsent(sum, i);
        }
        return false;
    }

    //https://leetcode.com/problems/trapping-rain-water/
    public int trap(int[] height) {
        int n = height.length;
        if(n == 0) return 0;

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = height[0];
        right[n - 1] = height[n - 1];

        int _lmax = left[0];
        int _rmax = right[n - 1];

        for(int i = 1; i < n; i++) {
            left[i] = Math.max(height[i], _lmax);
            _lmax = left[i];
        }

        for(int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], _rmax);
            _rmax = right[i];
        }
        int trappedWater = 0;
        for(int i = 0; i < n; i++) {
            int different = (Math.min(left[i], right[i]) - height[i]) * 1;
            trappedWater += different;
        }
        return trappedWater;
    }

    //https://leetcode.com/problems/count-sorted-vowel-strings/
    public int countVowelStrings(int n) {
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            q.offer(new int[]{i, 1});
        }
        int result = 0;

        while(!q.isEmpty()) {
            int[] pair = q.poll();
            int i = pair[0];
            int c = pair[1];
            if(c == n) {
                result++;
                continue;
            }
            for(int j = i; j < 5; j++) {
                q.offer(new int[]{j, c + 1});
            }
        }
        return result;
    }

    //https://leetcode.com/problems/wiggle-subsequence/
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n < 1) return n;

        int up = 1;
        int down = 1;

        for(int i = 1; i < n; i++) {
            if(nums[i] > nums[i - 1]) up = down + 1;
            else if(nums[i] < nums[i - 1]) down = up + 1;
        }
        return Math.max(up, down);
    }

    //https://leetcode.com/problems/ways-to-make-a-fair-array/
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] evens = new int[n];
        int[] odds = new int[n];
        evens[0] = nums[0];
        odds[0] = 0;

        for(int i = 1; i < n; i++) {
            if(i % 2 == 0) {
                evens[i] = evens[i - 1] + nums[i];
                odds[i] = odds[i - 1];
            }
            else {
                evens[i] = evens[i - 1];
                odds[i] = odds[i - 1] + nums[i];
            }
        }

        int even = 0;
        int odd = 0;
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                even = evens[i] - nums[i] + odds[n - 1] - odds[i];
                odd = odds[i]  + evens[n - 1] - evens[i];
            }
            else {
                even = evens[i] + odds[n - 1] - odds[i];
                odd = odds[i] - nums[i] + evens[n - 1] - evens[i];
            }
            if(even == odd) count++;
        }
        return count;
    }

}
