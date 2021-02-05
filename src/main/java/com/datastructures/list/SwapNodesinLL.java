package com.datastructures.list;

public class SwapNodesinLL {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode temp = head;
        int len = 0;
        while(temp != null) {
            temp = temp.next;
            len++;
        }
        int i = 1;
        ListNode node1 = null;
        ListNode node2 = null;
        temp = head;

        while(i <= len) {
            if(i == k) {
                node1 = temp;
            }
            if(i == len - k + 1) {
                node2 = temp;
            }
            i++;
            temp = temp.next;
        }
        int _t = node1.val;
        node1.val = node2.val;
        node2.val = _t;

        return head;
    }


     // Definition for singly-linked list.
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

}
