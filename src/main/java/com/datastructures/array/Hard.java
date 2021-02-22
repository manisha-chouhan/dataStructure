package com.datastructures.array;

public class Hard {

    //https://leetcode.com/problems/jump-game-ii/
    public int jump(int[] nums) {
        int n = nums.length;
        int[] jumps = new int[nums.length];
        for(int i = 1; i < n; i++)jumps[i] = Integer.MAX_VALUE;
        jumps[0] = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n && j <= i + nums[i]; j++) {
                jumps[j] = Math.min(jumps[j], 1 + jumps[i]);
            }
        }
        return jumps[n - 1];
    }
}
