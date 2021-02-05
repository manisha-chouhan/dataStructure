package com.datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SmallestSubtreeWithAllDeepestNodes {

    TreeNode subTree = null;
    boolean found = false;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        subTree = root;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        Set<TreeNode> set = new HashSet<>();

        while(!q.isEmpty()) {
            int size = q.size();
            set = new HashSet<>();
            while(size > 0) {
                TreeNode node = q.poll();
                size--;
                set.add(node);

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            level++;
        }
        helper(root, set);
        return subTree;
    }

    public int helper(TreeNode root, Set<TreeNode> set) {
        if(root == null) return 0;
        if(root.left == null && root.right == null && set.contains(root)) {
            if(set.size() == 1) {
                found = true;
                subTree = root;
            }
            return 1;
        }
        int left = helper(root.left, set);
        int right = helper(root.right, set);
        if(left + right == set.size() && !found) {
            found = true;
            subTree = root;
        }
        return left + right;
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
