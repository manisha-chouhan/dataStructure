package com.datastructures.graph;

import java.util.*;

public class VideosWatchedByFriends {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        List<String> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        visited.add(id);

        while(level > 0) {
            int size = q.size();
            while(size > 0) {
                int _id = q.poll();
                int[] _friends = friends[_id];
                for(int i = 0; i < _friends.length; i++) {
                    if(visited.contains(_friends[i])) continue;
                    visited.add(_friends[i]);
                    q.offer(_friends[i]);
                }
                size--;
            }
            level--;
        }
        HashMap<String, Integer> map = new HashMap<>();
        while(!q.isEmpty()) {
            int _id = q.poll();
            List<String> videos = watchedVideos.get(_id);
            for(String video : videos) {
                map.put(video, map.getOrDefault(video, 0) + 1);
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a,b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : a.getValue()-b.getValue()
        );

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }

        while(!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        return res;
    }
}
