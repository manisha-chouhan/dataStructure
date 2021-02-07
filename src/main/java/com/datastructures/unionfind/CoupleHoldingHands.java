package com.datastructures.unionfind;

import java.util.HashMap;

public class CoupleHoldingHands {
    public int minSwapsCouples(int[] row) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < row.length; i++) {
            map.put(row[i], i);
        }
        int swap = 0;
        for(int i = 0; i < row.length; i+=2) {
            int next = row[i] ^ 1;
            int index = map.get(next);
            if(index == i + 1)continue;
            swap++;
            //swap
            int temp = row[i + 1];
            map.put(row[i + 1], index);
            row[i + 1] = row[index];
            map.put(row[index], i + 1);
            row[index] = temp;
        }
        return swap;
    }
}
