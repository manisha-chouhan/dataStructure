package com.datastructures.design;

import java.util.HashMap;

//https://leetcode.com/problems/lru-cache/
class LRUCache {

    class DLNode {
        int key;
        int value;
        DLNode prev;
        DLNode next;
    }


    private DLNode popTail() {
        DLNode node = tail.prev;
        removeNode(node);
        return node;
    }

    private void addToHead(DLNode node) {
        node.next = head.next;
        head.next.prev = node;

        node.prev = head;
        head.next = node;
    }

    private void removeNode(DLNode node) {
        DLNode prev = node.prev;
        DLNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLNode node) {
        removeNode(node);
        addToHead(node);
    }



    private DLNode head, tail;
    private int size;
    private int capacity;
    private HashMap<Integer, DLNode> map;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        head = new DLNode();
        tail = new DLNode();
        head.next = tail;
        tail.prev = head;
        size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        DLNode node = map.get(key);
        if(node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            DLNode node = map.get(key);
            node.value = value;
            moveToHead(node);
        }
        else if(size <= capacity) {
            DLNode node = new DLNode();
            node.key = key;
            node.value = value;
            map.put(key, node);
            addToHead(node);
            size++;
        }
        if (size > capacity) {
            DLNode node = popTail();
            map.remove(node.key);
            size--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */