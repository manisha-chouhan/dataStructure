package com.datastructures.trees;

public class SmallestStringStartingFromLeaf {
    String res = "";
    public String smallestFromLeaf(TreeNode root) {
        helper(root, new StringBuilder());
        return res;
    }

    public void helper(TreeNode root, StringBuilder sb) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            sb.append((char)(root.val + 'a'));
            StringBuilder sb1 = new StringBuilder(sb.toString());
            sb1 = sb1.reverse();
            if((sb1.toString()).compareTo(res) < 0 || res.length() == 0 ) res = sb1.toString();
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append((char)(root.val + 'a'));
        helper(root.left, sb);
        helper(root.right, sb);
        sb.deleteCharAt(sb.length() - 1);
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
