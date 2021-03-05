package com.datastructures.array;

import java.util.*;

public class Medium {

    //https://leetcode.com/problems/contains-duplicate-iii/
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();

        for(int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long)nums[i]);
            if(floor != null && nums[i] - floor <= t)return true;

            Long ceil = set.ceiling((long)nums[i]);
            if(ceil != null && ceil - nums[i] <= t) return true;
            set.add((long)nums[i]);
            if(set.size() > k) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }

    //https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        if(n < 3)return list;
        Arrays.sort(nums);
        Set<String> seen = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int l = i + 1;
            int r = n - 1;
            int x = nums[i];

            while(l < r) {
                if(x + nums[l] + nums[r] == 0) {
                    String s = x + ":" + nums[l] + ":" + nums[r];
                    if(!seen.contains(s)) {
                        seen.add(s);
                        List<Integer> temp = new ArrayList<>();
                        temp.add(x);
                        temp.add(nums[l]);
                        temp.add(nums[r]);
                        list.add(temp);
                    }
                    l++;
                    r--;
                }
                else if(x + nums[l] + nums[r] > 0)r--;
                else l++;
            }
        }
        return list;
    }

    //https://leetcode.com/problems/reduce-array-size-to-the-half/
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int n = arr.length;
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() == a.getValue() ? a.getKey()- b.getKey() : b.getValue() - a.getValue());

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        int count = 0;
        int half = n/2;
        while(pq.size() > 0) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            n = n - entry.getValue();
            count++;
            if(n <= half)return count;
        }
        return count;
    }

    //https://leetcode.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[T.length];
        for(int i = 0; i < T.length; i++) {
            if(stack.isEmpty() || T[stack.peek()] >= T[i]) {
                stack.push(i);
            }
            else {
                while(!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int temp = stack.pop();
                    result[temp] = i - temp;
                }
                stack.push(i);
            }
        }
        while(!stack.isEmpty()) {
            result[stack.pop()] = 0;
        }
        return result;
    }

    //https://leetcode.com/problems/statistics-from-a-large-sample/
    public double[] sampleStats(int[] count) {
        double mode = 0.0;
        int max_freq = 0;
        double sum = 0.0;
        int totalElements = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < count.length; i++) {
            if(max_freq < count[i]) {
                max_freq = count[i];
                mode = (double)i;
            }
            min = count[i] != 0 ? Math.min(min, i) : min;
            max = count[i] != 0 ? Math.max(max, i) : max;

            sum += (double)(count[i] * i);
            totalElements += count[i];
        }

        double mean = sum/totalElements;

        int mid = totalElements/2;

        double[] result = new double[5];
        result[0] = (double)min;
        result[1] = (double)max;
        result[2] = mean;

        result[3] = helper(count, mid, min, max);
        if(totalElements % 2 == 0) {
            int secmed = helper(count,mid+1,min,max);
            result[3] = (result[3]+secmed)/2;
        }

        result[4] = mode;

        return result;
    }

    public int helper(int[] count,int get,int b,int e) {
        while(get>count[b]) {
            get = get-count[b++];
        }
        return b;
    }

    //https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels(String S) {
        HashMap<Character, int[]> map = new HashMap<>();
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(map.containsKey(ch)) {
                int[] temp = map.get(ch);
                temp[1] = i;
            }
            else {
                map.put(ch, new int[]{i, i});
            }
        }

        int i = 0;
        int j = map.get(S.charAt(0))[1];

        int len = S.length();
        List<Integer> list = new ArrayList<>();

        while(i < len && j < len) {
            int k = i+1;
            while(k < j) {
                int x = map.get(S.charAt(k))[1];
                j = Math.max(j, x);
                k++;
            }
            list.add(j - i + 1);
            i = j + 1;
            if(i < len) j = map.get(S.charAt(i))[1];
        }
        return list;
    }

    //https://leetcode.com/problems/max-consecutive-ones-iii/
    public int longestOnes(int[] A, int K) {
        TreeSet<Integer> index = new TreeSet<>();
        int i = 0;
        int j = 0;
        int len = A.length;
        int x = K;
        int maxLen = Integer.MIN_VALUE;
        int currLen = maxLen;
        while(i < len && j < len) {
            if(A[j] == 0) {
                index.add(j);
                if(x <= 0) {
                    currLen = i == 0 ? j : (j - i);
                    maxLen = Math.max(maxLen, currLen);
                    i = index.pollFirst() + 1;
                }
                else {
                    x--;
                }
            }
            j++;
        }
        currLen = j - i;
        return Math.max(maxLen, currLen);
    }

    //https://leetcode.com/problems/longest-well-performing-interval/
    public int longestWPI(int[] hours) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int max = 0;

        for(int i = 0; i < hours.length; i++) {
            sum  =  hours[i] > 8 ? sum + 1 : sum - 1;
            if(sum > 0) max = Math.max(max, i + 1);
            if(map.containsKey(sum)) {
                int index = map.get(sum);
                if(hours[index] <= 8) {
                    max = Math.max(max, i - index - 1);
                    map.put(sum, i);
                }
                else {
                    max = Math.max(max, 1 + i - index);

                }

            }
            else map.put(sum, i);
        }
        return max;
    }

    //https://leetcode.com/problems/next-greater-element-ii/
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++) {
            result[i] = -1;
        }

        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && (nums[i] >= nums[stack.peek()]) ) {
                stack.pop();
            }
            if(!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            stack.push(i);
        }
        System.out.println(stack.size());
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && (nums[i] >= nums[stack.peek()] )) {
                stack.pop();
            }
            if(!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            stack.push(i);
        }
        return result;
    }

    //https://leetcode.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Stack<int[]> stack = new Stack<>();
        int prev = -1;
        int i = 0;

        for(; i < intervals.length; i++) {
            prev = intervals[i][0];

            if(stack.isEmpty()) {
                stack.push(intervals[i]);
            }
            else {
                int end = intervals[i][1];
                while(!stack.isEmpty() && stack.peek()[1] >= intervals[i][0]) {
                    int[] temp = stack.pop();
                    prev = temp[0];
                    end = Math.max(end, temp[1]);
                }
                stack.push(new int[]{prev, end});
            }
        }
        int[][] result = new int[stack.size()][2];
        i = stack.size() - 1;

        while(!stack.isEmpty()) {
            result[i--] = stack.pop();
        }
        return result;
    }

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int res = 2*n;
        Map<Integer,Set<Integer>> map = new HashMap<>();
        for (int[] e: reservedSeats){
            int r = e[0], c = e[1];
            map.putIfAbsent(r, new HashSet<>());
            map.get(r).add(c);
        }
        for (int x: map.keySet()){
            res -= 2-deduct(map.get(x));
        }
        return res;
    }
    int deduct(Set<Integer> set){
        int mid = 1, left = 1, right = 1;
        if (set.contains(2) || set.contains(3)){
            left = 0;
        }
        if (set.contains(4) || set.contains(5)){
            mid = 0;
            left = 0;
        }
        if (set.contains(6) || set.contains(7)){
            mid = 0;
            right = 0;
        }
        if (set.contains(8) || set.contains(9)){
            right = 0;
        }
        if (mid == 1){
            return Math.max(left+right,mid);
        }else{
            return Math.max(left,right);
        }
    }
}
