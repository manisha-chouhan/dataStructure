package com.datastructures.heap;

import java.util.PriorityQueue;

public class Medium {

    //https://leetcode.com/problems/maximum-score-from-removing-stones/
    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(a);
        pq.offer(b);
        pq.offer(c);
        int count  = 0;
        while(pq.size() > 1) {
            int size = pq.size();
            int min = pq.poll();
            int temp = -1;
            if(size == 3){
                temp = pq.poll();
            }
            int max = pq.poll();
            if(temp != -1)
                pq.offer(temp);
            min -= 1;
            if(min != 0)pq.offer(min);
            max -= 1;
            if(max != 0)pq.offer(max);
            count++;
        }
        return count;
    }
}
