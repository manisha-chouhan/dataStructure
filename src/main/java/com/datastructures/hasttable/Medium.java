package com.datastructures.hasttable;

import java.util.HashMap;

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
}
