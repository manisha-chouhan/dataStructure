package com.datastructures.string;

import java.util.*;

public class Hard {
    //find if the given string is a valid number or not
    //https://leetcode.com/problems/valid-number/
    public boolean isNumber(String s) {
        boolean numberSeen = false;
        boolean eSeen = false;
        boolean dotSeen = false;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch))numberSeen = true;
            else if(ch == 'e'|| ch == 'E') {
                if(!numberSeen || eSeen)return false;
                eSeen = true;
                numberSeen = false;
            }
            else if(ch == '.') {
                if(eSeen || dotSeen) return false;
                dotSeen = true;
            }
            else if(ch == '-' || ch == '+') {
                if(i != 0) {
                    if(s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
                }
            }
            else return false;
        }

        return numberSeen;
    }

    //https://leetcode.com/problems/strong-password-checker/
    public int strongPasswordChecker(String s) {
        int missing = findMissing(s);
        int len = s.length();
        //System.out.println(len);
        List<Integer> repeatLens = countRepeatitions(s);

        if(len < 6) return Math.max(missing, 6 - len);
        else if(len >= 6 && len <= 20) {
            int swapCount = 0;
            for(int l : repeatLens) swapCount += l/3;
            return Math.max(missing, swapCount);
        }
        else if(len > 20) {
            int needToRemove = len - 20;
            int idx = 0;

            while(idx < repeatLens.size() && needToRemove > 0) {
                int l = repeatLens.get(idx);
                if(l % 3 == 0 ) {
                    //l--;
                    needToRemove--;
                    repeatLens.set(idx, l - 1);
                }

                idx++;
            }
            idx = 0;
            while(idx < repeatLens.size() && needToRemove > 0) {
                int l = repeatLens.get(idx);
                if(l % 3 == 1 && needToRemove > 1) {
                    //l -= 2;
                    needToRemove -= 2;
                    repeatLens.set(idx, l - 2);
                }
                //repeatLens.set(idx, l);
                idx++;
            }
            idx = 0;
            while(idx < repeatLens.size() && needToRemove > 0) {
                int l = repeatLens.get(idx);
                if(l >= 3 && needToRemove >= 3) {
                    l -= 3;
                    needToRemove -= 3;
                }
                repeatLens.set(idx, l);
                idx++;
            }

            int swapCount = 0;
            for(int l : repeatLens) swapCount += l/3;
            return Math.max(missing, swapCount) + len - 20;
        }
        return 0;
    }

    public int findMissing(String s) {
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;

        for(char ch : s.toCharArray()) {
            if(Character.isLowerCase(ch) && !hasLower) hasLower = true;
            else if(Character.isUpperCase(ch) && !hasUpper) hasUpper = true;
            else if(Character.isDigit(ch) && !hasDigit) hasDigit = true;
        }
        return (!hasUpper ? 1 : 0) + (!hasLower ? 1 : 0) + (!hasDigit ? 1 : 0);
    }

    public List<Integer> countRepeatitions(String s) {
        List<Integer> list = new ArrayList<>();
        int idx = 1;
        int count = 1;
        while(idx < s.length()) {
            if(s.charAt(idx) == s.charAt(idx - 1))count++;
            else {
                if(count >= 3){
                    list.add(count);
                }
                count = 1;
            }
            idx++;
        }
        if(count >= 3)list.add(count);
        return list;
    }

    //https://leetcode.com/problems/word-ladder/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord)) return 0;
        int len = beginWord.length();
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                String word = q.poll();
                size--;
                for(int i = 0; i < len; i++) {
                    for(int k = 0; k < 26; k++) {
                        String temp = word.substring(0, i) + (char)('a' + k) + word.substring(i + 1);
                        if(temp.equals(endWord)) return level + 2;
                        if(words.contains(temp) && !visited.contains(temp)) {
                            q.offer(temp);
                            visited.add(temp);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
