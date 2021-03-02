package com.datastructures.array;

import java.util.*;

public class Easy {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    //https://leetcode.com/problems/third-maximum-number/
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num : nums) {
            if(!pq.isEmpty() && pq.contains(num)) continue;
            if(pq.size() < 3) {
                pq.offer(num);
            }
            else {
                if(pq.peek() < num) {
                    pq.poll();
                    pq.offer(num);
                }
            }
        }
        if(pq.size() < 3) {
            while(pq.size() > 1) pq.poll();
        }
        return pq.poll();
    }

    //https://leetcode.com/problems/running-sum-of-1d-array/
    public int[] runningSum(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    //https://leetcode.com/problems/jewels-and-stones/
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> jewels = new HashSet<>();
        for(char ch : J.toCharArray()) {
            jewels.add(ch);
        }

        int count = 0;
        for(char ch : S.toCharArray()) {
            if(jewels.contains(ch))count++;
        }
        return count;
    }

    //https://leetcode.com/problems/relative-sort-array/
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num  : arr1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int i = 0;
        for(int num : arr2) {
            int count = map.get(num);
            map.remove(num);
            while(count > 0) {
                arr1[i] = num;
                count--;
                i++;
            }
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            int key = entry.getKey();
            while(count > 0) {
                arr1[i] = key;
                i++;
                count--;
            }
        }
        return arr1;
    }

    //https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
    public int specialArray(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : nums) {
            pq.offer(num);
        }

        for(int i = 1; i <= 100; i++) {
            while(!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            if(pq.size() == i) return i;
        }

        return -1;
    }

    //https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
    public boolean kLengthApart(int[] nums, int k) {
        int prev = -1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                if(prev != -1) {
                    if(i - prev - 1 < k)return false;
                }
                prev = i;
            }
        }
        return true;
    }

    //https://leetcode.com/problems/available-captures-for-rook/
    public int numRookCaptures(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 'R') {
                    int row = findInRow(i, j, board, n, m);
                    int col = findInCol(i, j, board, n, m);
                    return row + col;
                }
            }

        }
        return 0;
    }

    public int findInRow(int x, int y, char[][] board, int n, int m) {
        //find plus row
        int i = x;
        int count = 0;
        for(int j = y + 1; j < m; j++) {
            if(board[i][j] == 'p') {
                count++;
                break;
            }
            else if(board[i][j] == 'B')break;
        }

        for(int j = y - 1; j >= 0; j--) {
            if(board[i][j] == 'p') {
                count++;
                break;
            }
            else if(board[i][j] == 'B')break;
        }
        return count;
    }

    public int findInCol(int x, int y, char[][] board, int n, int m) {
        //find plus row
        int j = y;
        int count = 0;
        for(int i = x + 1; i < n; i++) {
            if(board[i][j] == 'p') {
                count++;
                break;
            }
            else if(board[i][j] == 'B')break;
        }

        for(int i = x - 1; i >= 0; i--) {
            if(board[i][j] == 'p') {
                count++;
                break;
            }
            else if(board[i][j] == 'B')break;
        }
        return count;
    }

    //https://leetcode.com/problems/three-consecutive-odds/
    public boolean threeConsecutiveOdds(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] % 2 != 0) {
                int j = i + 1;
                boolean flag = true;
                while(j < i + 3 && j < arr.length) {
                    if(arr[j] % 2 == 0) {
                        flag = false;
                        break;
                    }
                    j++;
                }
                if(flag && i + 2 < arr.length) return true;
            }
        }
        return false;
    }
}
