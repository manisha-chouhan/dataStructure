package com.datastructures.string;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Easy {
    public boolean rotateString(String A, String B) {
        if(A.equals(B)) return true;
        List<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < A.length(); i++) {
            if(A.charAt(i) == B.charAt(0))indexes.add(i);
        }

        for(int i : indexes) {
            String temp = A.substring(i) + A.substring(0, i);
            if(temp.equals(B)) return true;
        }
        return false;
    }

    //https://leetcode.com/problems/defanging-an-ip-address/
    public String defangIPaddr(String address) {
        String[] str = address.split("\\.");
        StringBuilder sb = new StringBuilder();
        sb.append(str[0]);
        for(int i = 1; i < str.length; i++) {
            sb.append("[.]");
            sb.append(str[i]);
        }
        return sb.toString();
    }

    //https://leetcode.com/problems/shortest-distance-to-a-character/
    public int[] shortestToChar(String s, char c) {
        int len = s.length();
        int[] res = new int[len];
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < len; i++) {
            if(s.charAt(i) == c) {
                set.add(i);
            }
        }
        for(int i = 0; i < len ; i++) {
            Integer floor = set.floor(i);
            Integer ceil = set.ceiling(i);
            int f = Integer.MAX_VALUE;
            int _c = Integer.MAX_VALUE;
            if(floor != null) {
                f = i - floor;
            }
            if(ceil != null) {
                _c = ceil - i;
            }

            res[i] = Math.min(f, _c);
        }
        return res;
    }

    //https://leetcode.com/problems/string-matching-in-an-array/
    public List<String> stringMatching(String[] words) {
        List<String>[] arr = new ArrayList[30];
        for (int i = 0; i < 30; i++) {
            arr[i] = new ArrayList<>();
        }

        for (String word : words) {
            int len = word.length();
            arr[len - 1].add(word);
        }
        List<String> result = new ArrayList<>();

        for (String word : words) {
            int len = word.length();
            boolean flag = false;
            for (int j = len; j < 30; j++) {
                List<String> list = arr[j];
                for (String s : list) {
                    if (s.contains(word)) {
                        result.add(word);
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
        }
        return result;
    }

    //https://leetcode.com/problems/slowest-key/
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int prevTime = 0;
        int max = Integer.MIN_VALUE;
        char key = keysPressed.charAt(0);

        for(int i = 0; i < releaseTimes.length; i++) {
            int temp = releaseTimes[i] - prevTime;
            if(temp > max) {
                max = temp;
                key = keysPressed.charAt(i);
            }
            else if(temp == max){
                if(key < keysPressed.charAt(i)) key = keysPressed.charAt(i);
            }
            prevTime = releaseTimes[i];
        }
        return key;
    }

    //https://leetcode.com/problems/delete-columns-to-make-sorted/
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int count = 0;
        for(int j = 0; j < m; j++) {
            char prev = '-';
            for(int i = 0; i < n; i++) {
                char ch = strs[i].charAt(j);
                if(prev == '-') {
                    prev = ch;
                    continue;
                }
                if(ch < prev) {
                    count++;
                    break;
                }
                prev = ch;
            }
        }
        return count;
    }
}
