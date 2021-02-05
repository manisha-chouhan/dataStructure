package com.datastructures.array.suffix;

public class LastSubstringInLexiOrder {
    public String lastSubstring(String s) {
        int max = s.charAt(0);

        for(char c : s.toCharArray()) {
            max = c > max ? c : max;
        }

        String res = s;

        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            if(s.charAt(i) == max) {
                String curr = s.substring(i);
                if(curr.compareTo(res) > 0) res = curr;
            }
        }
        return res;
    }
}
