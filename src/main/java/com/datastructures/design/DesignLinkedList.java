package com.datastructures.design;

public class DesignLinkedList {
    int size;
    Node head;
    /** Initialize your data structure here. */
    public DesignLinkedList() {
        this.size = 0;
        this.head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index < 0 || index >= size) return -1;
        Node temp = head;
        int i = 0;
        while(i <= index && temp != null) {
            if(i == index) return temp.val;
            temp = temp.next;
            i++;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        if(head != null) {
            Node temp = head;
            head = node;
            node.next = temp;
        }else head = node;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if(head == null) {
            head = node;
            size++;
            return;
        }
        Node temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size) return;
        Node node = new Node(val);
        Node temp = head;
        int i = 0;
        Node prev = null;
        if(index == 0) {
            head = node;
            node.next = temp;
        }
        else {
            boolean added = false;
            while(temp != null) {
                if(i == index) {
                    prev.next = node;
                    node.next = temp;
                    break;
                }
                prev = temp;
                temp = temp.next;
                i++;
            }
            if(index == size) {
                prev.next = node;
                node.next = temp;
            }

        }
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index >= size  || index < 0) return;
        Node temp = head;
        int i = 0;
        Node prev = null;
        if(index == 0) {
            head = head.next;
            temp.next = null;
        }
        else {
            while(temp.next != null) {
                if(i == index) {
                    prev.next = temp.next;
                    temp.next = null;
                    break;
                }
                prev = temp;
                temp = temp.next;
                i++;
            }
            if(index == size - 1) {
                prev.next = null;
            }

        }
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
