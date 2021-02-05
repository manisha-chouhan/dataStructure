package com.datastructures.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AllOneDS {

        HashMap<String, Integer> map;
        PriorityQueue<Data> minQ;
        PriorityQueue<Data> maxQ;
        HashMap<String, Data> mapData;

        /** Initialize your data structure here. */
        public AllOneDS() {
            map = new HashMap<>();
            minQ = new PriorityQueue<>(new minComparator());
            maxQ = new PriorityQueue<>(new maxComparator());
            mapData = new HashMap<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {

            if(map.containsKey(key)) {
                int value = map.get(key);
                value++;
                Data data = mapData.get(key);
                minQ.remove(data);
                maxQ.remove(data);
                data = new Data(key, value);
                minQ.offer(data);
                maxQ.offer(data);
                map.put(key, value);
                mapData.put(key, data);
            }else {
                Data data = new Data(key, 1);
                minQ.offer(data);
                maxQ.offer(data);
                map.put(key, 1);
                mapData.put(key, data);
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if(map.containsKey(key)) {
                int value = map.get(key);
                value--;

                Data data = mapData.get(key);
                minQ.remove(data);
                maxQ.remove(data);

                if(value == 0) {
                    map.remove(key);
                    mapData.remove(key);
                }
                else {
                    data = new Data(key, value);
                    map.put(key, value);
                    minQ.offer(data);
                    maxQ.offer(data);
                    mapData.put(key, data);
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if(!maxQ.isEmpty()) return maxQ.peek().key;
            else return "";
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            //System.out.println("map hello is " + map.get("hello"));
            //System.out.println("map leet is " + map.get("leet"));
            //System.out.println("min q sizeminQ " + minQ.size());
            if(!minQ.isEmpty()) return minQ.peek().key;
            else return "";
        }


        class Data {
            String key;
            int val;

            Data(String key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        public class minComparator implements Comparator<Data> {
            @Override
            public int compare(Data x, Data y) {
                String key1 = x.key;
                String key2 = y.key;

                if (x.val == y.val) {
                    return key1.compareTo(key2);
                }
                return x.val - y.val;
            }
        }

        public class maxComparator implements Comparator<Data> {
            @Override
            public int compare(Data x, Data y) {
                String key1 = x.key;
                String key2 = y.key;

                if (x.val == y.val) {
                    return key1.compareTo(key2);
                }
                return y.val - x.val;
            }
        }


/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}
