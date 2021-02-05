package com.datastructures.string;

import java.util.ArrayList;
import java.util.List;

public class CommonChars {
    public List<String> commonChars(String[] A) {
        int[] count = new int[26];

        for(char ch : A[0].toCharArray()) {
            count[ch - 'a']++;
        }

        for(String s : A) {
            int[] _count = new int[26];
            for(char ch : s.toCharArray()) {
                _count[ch - 'a']++;
            }
            for(int i = 0; i < 26; i++) {
                count[i] = Math.min(count[i], _count[i]);
            }
        }

        List<String> res = new ArrayList<>();

        for(int i = 0; i < 26; i++) {
            int j = count[i];
            while(j > 0) {
                res.add((char)(i + 'a') + "");
                j--;
            }
        }
        return res;
    }
}
