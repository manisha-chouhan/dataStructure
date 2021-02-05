package com.datastructures.array;

import java.util.HashMap;
import java.util.HashSet;

public class GoodPairs {
        public int numIdenticalPairs(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for(int i = 0; i < nums.length; i++) {
                if(map.containsKey(nums[i])) {
                    count += map.get(nums[i]);
                }
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
            return count;
        }
}

//----Average waiting time for Customers in Restaurants
class Solution {
    public double averageWaitingTime(int[][] customers) {
        int time = customers[0][0];
        double avgTime = 0;
        for(int i = 0; i < customers.length; i++) {
            int[] customer = customers[i];
            if(customer[0] > time) time = customer[0];
            int waitTime = time + customer[1] - customer[0];
            time += customer[1];
            avgTime += waitTime;
        }
        return avgTime/customers.length;
    }
}

class GoalParser {
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < command.length();) {
            char ch = command.charAt(i);
            if(ch != ')' && ch != '(') {
                sb.append(ch);
                i++;
            }
            else if(ch == '(') {
                i++;
                if(command.charAt(i) == ')') {
                    sb.append("o");
                    i++;
                }
                else {
                    while(i < command.length() && command.charAt(i) != ')') {
                        sb.append(command.charAt(i));
                        i++;
                    }
                    i++;
                }
            }
        }
        return sb.toString();
    }

    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();

        for(char ch : str.toCharArray()) {
            if(ch >= 'A' && ch <= 'Z') {
                sb.append((char)(ch + 32));
            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] arr = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet<String> set = new HashSet<>();

        for(String word : words) {
            StringBuilder sb =  new StringBuilder();
            for(char ch : word.toCharArray()) {
                sb.append(arr[ch - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length();) {
            if((i + 2 < s.length() && s.charAt(i + 2) != '#') || i + 2 >= s.length()) {
                sb.append((char)((Character.getNumericValue(s.charAt(i) - 1) + 'a')));
                i += 1;
            }
            else {
                String str = s.substring(i, i + 2);
                //System.out.println(str + " str is");
                int num = Integer.parseInt(str) - 1;
                sb.append((char)(num + 'a'));
                i += 3;
            }

        }
        return sb.toString();
    }
}