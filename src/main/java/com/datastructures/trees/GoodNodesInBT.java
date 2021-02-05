package com.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public class GoodNodesInBT {
    int count = 0;
    public int goodNodes(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Integer prev = null;
        helper(root, list, prev);
        return count;
    }

    public void  helper(TreeNode root, List<Integer> list, Integer prev) {
        if(root == null) return;
        if(prev == null) {
            prev = root.val;
            count++;
        }else {
            if(root.val >= prev) {
                prev = root.val;
                count++;
            }
        }
        helper(root.left, list, prev);
        helper(root.right, list, prev);

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
