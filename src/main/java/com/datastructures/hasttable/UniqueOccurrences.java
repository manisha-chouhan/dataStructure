package com.datastructures.hasttable;

import java.util.HashMap;
import java.util.HashSet;

public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        HashSet<Integer> seen = new HashSet<>();
        for(int key : map.keySet()) {
            if(seen.contains(map.get(key))) return false;
            seen.add(map.get(key));
        }

        return true;
    }
}
