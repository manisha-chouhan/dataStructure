package com.datastructures.list;

public class Medium {

    public class ListNode {
      int val;
      ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;

        ListNode prev = null;
        ListNode node = head;

        while(node != null) {
            ListNode temp = node.next;
            if(temp != null && node.val == temp.val) {
                while(temp != null && temp.val == node.val) {
                    temp = temp.next;
                }
                if(prev == null) {
                    head = temp;
                }
                else {
                    prev.next = temp;
                }
                node = temp;
            }
            else {
                prev = node;
                node = node.next;
            }

        }
        return head;
    }
}
