package com.datastructures.hasttable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Easy {

    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String domains : cpdomains) {
            String[] arr = domains.split(" ");
            int count = Integer.parseInt(arr[0]);
            String domain = arr[1];
            map.put(domain, map.getOrDefault(domain, 0) + count);

            arr = domain.split("\\.");
            StringBuilder sb = new StringBuilder();

            for(int i = arr.length - 1; i > 0; i--) {
                if(sb.length() == 0) sb.append(arr[i]);
                else {
                    sb.insert(0, ".");
                    sb.insert(0, arr[i]);
                }
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + count);
            }
        }
        List<String> res = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            res.add(entry.getValue() +" " +entry.getKey());
        }
        return res;
    }

    public int sumOfUnique(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int sum = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1)sum += entry.getKey();
        }
        return sum;
    }
}
