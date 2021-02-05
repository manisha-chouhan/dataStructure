package com.datastructures.heap;

import java.util.*;

public class DistantBarcodes {
        public int[] rearrangeBarcodes(int[] barcodes) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : barcodes) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            }

            int index = 0;
            while (pq.size() > 1) {
                int[] t1 = pq.poll();
                int[] t2 = pq.poll();
                barcodes[index++] = t1[0];
                barcodes[index++] = t2[0];
                if (t1[1] - 1 != 0)
                    pq.add(new int[]{t1[0], t1[1] - 1});
                if (t2[1] - 1 != 0)
                    pq.add(new int[]{t2[0], t2[1] - 1});
            }
            if (pq.size() != 0) {
                int[] t = pq.poll();
                barcodes[index] = t[0];
            }
            return barcodes;
        }
}
