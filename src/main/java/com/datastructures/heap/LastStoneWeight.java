package com.datastructures.heap;

import java.util.PriorityQueue;

public class LastStoneWeight {
        public int lastStoneWeight(int[] stones) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            for(int stone : stones) {
                pq.add(stone);
            }
            while(pq.size() > 1) {
                int y = pq.poll();
                int x = pq.poll();

                if(x == y) continue;
                else pq.add(y - x);
            }
            return pq.size() != 0 ? pq.poll() : 0;
        }
}
