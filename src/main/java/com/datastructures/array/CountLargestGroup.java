package com.datastructures.array;

import java.util.HashMap;
import java.util.Map;

public class CountLargestGroup {
    public int countLargestGroup(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 1; i <= n; i++) {
            if(i >= 1 && i <= 9) {
                map.put(i, 1);
                continue;
            }
            int x = i;
            int sum = 0;
            while(x > 0) {
                sum += x%10;
                x = x/10;
            }
            //System.out.println("i is : " + i + " sum is : " + sum);
            map.put(sum, map.getOrDefault(sum, 0) + 1);

        }

        int count = 0;
        int size = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(size < entry.getValue()) {
                size = entry.getValue();
                count = 1;
            }
            else if(size == entry.getValue()) count++;

        }
        return count;
    }
}
