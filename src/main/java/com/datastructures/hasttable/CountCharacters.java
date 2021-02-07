package com.datastructures.hasttable;

public class CountCharacters {
    public int countCharacters(String[] words, String chars) {
        int[] count = new int[26];

        for(char ch : chars.toCharArray()) {
            count[ch - 'a']++;
        }
        int res = 0;
        for(String word : words) {
            int[] _count  = new int[26];
            for(char ch : word.toCharArray()) {
                _count[ch - 'a']++;
            }
            boolean flag = true;
            for(int i = 0; i < 26; i++) {
                if(_count[i] > count[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) res += word.length();
        }
        return res;
    }
}
