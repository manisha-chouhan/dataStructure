package com.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public class FlattenBTtoLL {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        helper(root, list);
        for(int i = 1; i < list.size(); i++) {
            list.get(i - 1).right = list.get(i);
        }
    }

    public void helper(TreeNode root, List<TreeNode> list) {
        if(root == null) return;
        list.add(root);
        helper(root.left, list);
        helper(root.right, list);
        root.left = null;
        root.right = null;
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
