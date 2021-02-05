package com.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class EvenOddTree {
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            int prev = -1;
            for(int i = 0; i < size; i++) {

                TreeNode node = q.poll();
                if(i == 0) prev = node.val;
                else if(level % 2 == 0) {
                    //increasing
                    if(node.val <= prev) return false;
                    prev = node.val;
                }
                else {
                    //decreasing
                    if(node.val >= prev) return false;
                    prev = node.val;
                }
                if(level % 2 == 0) {
                    if(node.val % 2 == 0) return false;
                } else if(node.val % 2 != 0) return false;
                if(node.left != null)q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            level++;
        }
        return true;
    }

    //Definition for a binary tree node.
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
