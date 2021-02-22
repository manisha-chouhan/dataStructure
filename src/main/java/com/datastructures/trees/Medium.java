package com.datastructures.trees;

import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Medium {

    //https://leetcode.com/problems/validate-binary-search-tree/
    public boolean isValidBSTRec(TreeNode root, Integer lower, Integer upper) {
        if(root == null) return true;
        if((lower != null && root.val <= lower) || (upper != null && root.val >= upper)) return false;
        return isValidBSTRec(root.left, lower, root.val) && isValidBSTRec(root.right, root.val, upper);
    }
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;

        return isValidBSTRec(root, null, null);
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

    //https://leetcode.com/problems/trim-a-binary-search-tree/
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return null;
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        if(root.val < L || root.val > R) {
            return root.left != null ? root.left : root.right;
        }
        return root;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    //https://leetcode.com/problems/n-ary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            while(size > 0) {
                Node node = q.poll();
                list.add(node.val);
                size--;
                if(node.children == null) continue;
                for(Node child : node.children) {
                    q.offer(child);
                }
            }
            result.add(list);
        }

        return result;
    }

}
