package com.datastructures.array;

import javafx.scene.control.TreeCell;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(7);
        TreeNode node = new TreeNode(45);

        int distance = findDistance(root, node, root.left.right.left );
        System.out.println( distance);
    }

        //find distance between given 2 nodes in the tree
        public static int findDistance(TreeNode root, TreeNode node1, TreeNode node2) {
            if(root == null || node1 == null || node2 == null) return -1;

            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();

            List<Integer> list1 = new LinkedList<>();
            List<Integer> list2 = new LinkedList<>();

            findPath(root, node1, list1, q1);
            findPath(root, node2, list2, q2);
            int distance = 0;
            if(q1.isEmpty() || q2.isEmpty()) return -2;

                while(!q1.isEmpty() && !q2.isEmpty() && q1.peek() == q2.peek()) {
                q1.poll();
                q2.poll();
            }
            distance = q1.size() + q2.size();
            return distance;
        }

        public static void findPath(TreeNode root, TreeNode node1, List<Integer> list, Queue<Integer> q) {
            if(root == null) return;
            if(root == node1) {
                list.add(root.val);
                for(int i : list) {
                    q.offer(i);
                }
                return;
            }
            list.add(root.val);
            findPath(root.left, node1, list, q);
            findPath(root.right, node1, list, q);
            list.remove(list.size() - 1);
        }


        static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int val) {
                this.val = val;
                left = null;
                right = null;
            }
        }

       //array + , - numbers longest subarray with sum == 0
    //[5, 2,-2, 3, 1, -2, -2, -5]
    // 5 -> 0,  7 -> 1, i = 2 sum = 5 index = 0 max = 2 - 0  sum = 7 i = 5 index = 1 len 5 - 1 i = 6 index = 0 len = 6

    //
    public static int findLongestSubArray(int[] arr) {
        int sum = 0;
        int max = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(sum, -1);
        for(int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(map.containsKey(sum)) {
                max = Math.min(max, i - map.get(sum));
            }
            map.put(sum, i);
        }
        return max;
    }
}
