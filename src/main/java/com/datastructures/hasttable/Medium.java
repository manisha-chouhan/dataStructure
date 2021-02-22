package com.datastructures.hasttable;

import java.util.*;

public class Medium {

    /*
    * Given an array of strings names of size n. You will create n folders in your file system such that,
    *  at the ith minute, you will create a folder with the name names[i].

     Since two files cannot have the same name, if you enter a folder name which is previously used,
     * the system will have a suffix addition to its name in the form of (k),
     * where, k is the smallest positive integer such that the obtained name remains unique.
     Return an array of strings of length n where ans[i] is the actual name the system
     *  will assign to the ith folder when you create it.*/

     public String[] getFolderNames(String[] names) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < names.length; i++) {
            String temp = names[i];
            String name = names[i];
            if(map.containsKey(name)) {
                int k = map.get(name);
                while(true) {
                    temp = name + "(" + k + ")";
                    if(!map.containsKey(temp)) {
                        map.put(name, k++);
                        break;
                    }
                    else k++;

                }
                names[i] = temp;
            }
            map.put(names[i], 1);
        }
        return names;
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        TreeMap<String, HashMap<String, Integer>> map = new TreeMap<>((a, b) -> Integer.parseInt(a) - Integer.parseInt(b));
        TreeSet<String> foodItems = new TreeSet<>();

        for(List<String> order : orders) {
            String table = order.get(1);
            String food = order.get(2);
            foodItems.add(food);
            map.putIfAbsent(table, new HashMap<>());
            HashMap<String, Integer> _map = map.get(table);
            _map.put(food, _map.getOrDefault(food, 0) + 1);
        }

        List<List<String>> res = new LinkedList<>();
        List<String> list = new LinkedList<>();
        list.add("Table");

        for(String foodItem : foodItems) {
            list.add(foodItem);
        }
        res.add(list);

        for(Map.Entry<String, HashMap<String, Integer>> entry : map.entrySet()) {
            String table = entry.getKey();
            HashMap<String, Integer> _map = entry.getValue();
            list = new LinkedList<>();
            list.add(table);

            for(String foodItem : foodItems) {
                if(_map.containsKey(foodItem)) list.add(_map.get(foodItem) + "");
                else list.add("0");
            }
            res.add(list);
        }

        return res;
    }
}
