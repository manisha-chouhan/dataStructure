package com.datastructures.design;

import java.util.TreeMap;


//https://leetcode.com/problems/snapshot-array/
public class SnapshotArray {
    int snap_id = 0;
    TreeMap<Integer, Integer>[] map;
    public SnapshotArray(int length) {
        map = new TreeMap[length];
        for(int i = 0; i < length; i++) {
            map[i] = new TreeMap<>();
        }
    }

    public void set(int index, int val) {
        map[index].put(snap_id, val);
    }

    public int snap() {
        int temp = snap_id;
        snap_id++;
        return temp;
    }

    public int get(int index, int snap_id) {
        Integer floor = map[index].floorKey(snap_id);
        return floor == null ? 0 : map[index].get(floor);
    }
}
