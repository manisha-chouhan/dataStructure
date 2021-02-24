package com.datastructures.string;

import java.util.*;

public class Medium {

    //https://leetcode.com/problems/reverse-words-in-a-string/
    public String reverseWords(String s) {
        String[] str = s.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for(int i = str.length - 1; i >= 0; i--) {
            sb.append(str[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    //https://leetcode.com/problems/compare-version-numbers/
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int i = 0;
        int l1 = v1.length;
        int l2 = v2.length;
        while(i < l1 && i < l2) {
            int n1 = Integer.parseInt(v1[i]);
            int n2 = Integer.parseInt(v2[i]);

            if(n1 > n2) return 1;
            if(n1 < n2) return -1;
            i++;
        }

        while(i < l1) {
            int n1 = Integer.parseInt(v1[i]);
            if(n1 != 0) return 1;
            i++;
        }

        while(i < l2) {
            int n2 = Integer.parseInt(v2[i]);
            if(n2 != 0) return -1;
            i++;
        }
        return 0;
    }

    //https://leetcode.com/problems/find-and-replace-pattern/
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for(String word : words) {
            HashMap<Character, Character> map = new HashMap<>();
            HashMap<Character, Character> _map = new HashMap<>();
            boolean flag = true;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                char p = pattern.charAt(i);
                if(!map.containsKey(p) && !_map.containsKey(ch)) {
                    map.put(p, ch);
                    _map.put(ch, p);
                }
                else if((map.containsKey(p) && map.get(p) != ch) || (_map.containsKey(ch) && _map.get(ch) != p)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                res.add(word);
            }
        }
        return res;
    }

    //https://leetcode.com/problems/custom-sort-string/
    public String customSortString(String S, String T) {
        int[] arr = new int[26];

        for(int i = 0; i < T.length(); i++) {
            arr[T.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();

        for(char ch : S.toCharArray()) {
            if(arr[ch - 'a'] != 0) {
                int count  = arr[ch - 'a'];
                while(count > 0) {
                    sb.append(ch);
                    count--;
                }
                arr[ch - 'a'] = 0;
            }
        }
        for(int i = 0; i < 26; i++) {
            if(arr[i] != 0) {
                int count  = arr[i];
                while(count > 0) {
                    sb.append((char)(i + 'a'));
                    count--;
                }
                arr[i] = 0;
            }
        }
        return sb.toString();
    }

    //https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
    public int minInsertions(String s) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        int count = 0;
        while(i < s.length()) {
            char ch = s.charAt(i);
            if(ch == '(') {
                stack.push(ch);
            }else {
                int j = i + 1;
                if(stack.isEmpty() || stack.peek() != '(')count++;
                else stack.pop();
                if(j >= s.length() || s.charAt(j) != ')') {
                    count++;
                }else i++;
            }
            i++;
        }
        while(!stack.isEmpty()) {
            char ch = stack.pop();
            if(ch == '(')count += 2;
        }
        return count;
    }

    //https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
    public String smallestSubsequence(String s) {
        int[] count = new int[26];
        for(char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()) {
            count[ch - 'a']--;
            if(visited[ch - 'a'])continue;

            while(!stack.isEmpty() && ch < stack.peek() && count[stack.peek() - 'a'] > 0) {
                char temp = stack.pop();
                visited[temp - 'a'] = false;
            }
            stack.push(ch);
            visited[ch - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }


    char[] arr = {'a', 'b', 'c'};
    //https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
    public String getHappyString(int n, int k) {
        String s = "";
        List<String> list = new ArrayList<>();
        helper(s, list, n);
        Collections.sort(list);
        return list.size() < k ? "" : list.get(k - 1);
    }

    public void helper(String s, List<String> list, int n) {
        if(s.length() == n) {
            list.add(s);
            return;
        }
        for(int j = 0; j < 3; j++) {
            if(s.length() == 0) {
                String temp = new String(s);
                s = s +""+ arr[j];
                helper(s, list, n);
                s = temp;
            }
            else {
                if(s.charAt(s.length() - 1) == arr[j])continue;
                else {
                    String temp = new String(s);
                    s = s + ""+arr[j];
                    helper(s, list, n);
                    s = temp;
                }
            }
        }
    }
}
