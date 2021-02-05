package com.datastructures.string;

import java.util.ArrayList;
import java.util.List;

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

}
