package com.datastructures.graph;

import java.util.*;

public class Easy {

    // Definition for a Node.
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

    //https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
    public int maxDepth(Node root) {
        int level = 0;
        if(root == null) return 0;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                Node node = q.poll();
                size--;
                if(node.children == null) continue;
                for(Node child : node.children) {
                    q.offer(child);
                }
            }
            level++;
        }
        return level;
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

    //https://leetcode.com/problems/symmetric-tree/
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) return true;
        if((node1 == null && node2 != null) || (node1 != null && node2 == null)) return false;
        return node1.val == node2.val && helper(node1.left, node2.right) && helper(node1.right, node2.left);
    }

    //https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            while(size > 0) {
                TreeNode node = q.poll();
                list.add(node.val);
                size--;
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null) {
                    q.offer(node.right);
                }
            }
            result.add(0, list);
        }

        return result;
    }

    int min_depth = Integer.MAX_VALUE;
    //https://leetcode.com/problems/minimum-depth-of-binary-tree/
    public int minDepth(TreeNode root) {
        helper(root, 1);
        return min_depth == Integer.MAX_VALUE ? 0 : min_depth;
    }

    public void helper(TreeNode root, int depth) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            min_depth = Math.min(min_depth, depth);
        }

        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }

    //https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < salary.length; i++) {
            min = Math.min(min, salary[i]);
            max = Math.max(max, salary[i]);
        }
        int count = 0;
        int sum = 0;
        for(int i = 0; i < salary.length; i++) {
            if(min != salary[i] && max != salary[i]) {
                sum += salary[i];
                count++;
            }
        }
        return ((double)sum/count);
    }

    //https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;
        for(int i = 0; i < arr1.length; i++) {
            boolean flag = true;
            for(int j = 0; j < arr2.length; j++) {
                if(Math.abs(arr1[i] - arr2[j]) <= d) {
                    flag = false;
                    break;
                }
            }
            if(flag)count++;
        }
        return count;
    }
}
