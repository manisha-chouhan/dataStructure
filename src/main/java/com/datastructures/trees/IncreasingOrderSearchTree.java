package com.datastructures.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class IncreasingOrderSearchTree {
    TreeSet<TreeNode> set;
    public TreeNode increasingBST(TreeNode root) {
        set = new TreeSet<>((a, b) -> a.val - b.val);

        helper(root);

        TreeNode leftMost = null;
        root = null;
        for(TreeNode node : set) {
            if(leftMost == null){
                leftMost = node;
                root = node;
            }else {
                root.right = node;
                root = node;
            }
        }
        return leftMost;
    }

    public void helper(TreeNode root) {
        if(root == null) return;
        helper(root.left);
        set.add(root);
        helper(root.right);
        root.left = null;
        root.right = null;
    }
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

    /*
        A binary tree is univalued if every node in the tree has the same value.
        Return true if and only if the given tree is univalued.
    */
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        if(root.left != null) {
            if(root.left.val != root.val) return false;
        }
        if(root.right != null) {
            if(root.right.val != root.val) return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper1(root, res);
        return res;
    }

    public void helper1(TreeNode root, List<Integer> res) {
        if(root == null) return;
        helper1(root.left, res);
        helper1(root.right, res);
        res.add(root.val);
        return;
    }
}


