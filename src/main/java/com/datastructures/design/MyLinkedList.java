package com.datastructures.design;

//https://leetcode.com/problems/design-linked-list/
class MyLinkedList {


    int size;
    Node head;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.size = 0;
        this.head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(head == null || index >= size) return -1;
        int i = 0;
        Node node = head;
        while(i < index) {
            node = node.next;
            i++;
        }
        return node.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if(head == null) {
            head = new Node(val);
            size++;
            return;
        }
        else {
            Node node = new Node(val);
            node.next = head;
            head = node;
        }
        size++;

    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if(head == null) {
            head = node;
            size++;
        }
        else {
            Node temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
            size++;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size) return;
        if(index == 0) {
            this.addAtHead(val);
            return;
        }

        if(index == size) {
            this.addAtTail(val);
            return;
        }
        Node node = new Node(val);
        if(head == null) {
            head = node;
            size++;
            return;
        }
        else {
            int i = 0;
            Node temp = head;
            Node prev = null;
            while(i < index && temp != null) {
                prev =  temp;
                temp = temp.next;
                i++;
            }
            prev.next = node;
            node.next = temp;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;
        if(index == 0) {
            Node temp = head;
            head = head.next;
            temp.next = null;
            size--;
            return;
        }
        int i = 0;
        Node temp = head;
        Node prev = null;
        while(i < index && temp != null) {
            i++;
            prev = temp;
            temp = temp.next;
        }
        prev.next = temp.next;
        temp.next = null;
        size--;
    }



    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */