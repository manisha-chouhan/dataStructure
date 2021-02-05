package com.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SumEvenGrandparent {
    public int sumEvenGrandparent(TreeNode root) {
        Queue<Qentry> q = new LinkedList<>();
        q.offer(new Qentry(root, -1));
        int sum = 0;
        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                Qentry entry = q.poll();
                TreeNode node = entry.node;
                int pValue = entry.p_value;

                if(node.left != null) {
                    q.offer(new Qentry(node.left, node.val));
                    if(pValue != -1 && pValue % 2 == 0) sum += node.left.val;
                }
                if(node.right != null) {
                    q.offer(new Qentry(node.right, node.val));
                    if(pValue != -1 && pValue % 2 == 0) sum += node.right.val;
                }
            }
        }
        return sum;
    }

    class Qentry {
        TreeNode node;
        int p_value;

        Qentry(TreeNode node, int p_value) {
            this.p_value = p_value;
            this.node = node;
        }
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
