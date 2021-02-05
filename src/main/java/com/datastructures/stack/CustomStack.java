package com.datastructures.stack;

import java.util.Stack;

class CustomStack {
    Stack<Integer> stack;
    int size = 0;
    int maxSize = 0;
    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Stack<>();
    }

    public void push(int x) {
        if(size < maxSize) {
            stack.push(x);
            size++;
        }
    }

    public int pop() {
        if(!stack.isEmpty()) {
            size--;
            return stack.pop();
        }
        return -1;
    }

    public void increment(int k, int val) {
        if(stack.isEmpty()) return;
        Stack<Integer> ts = new Stack<>();
        int min = Math.min(k, size);
        int i = 0;
        while(!stack.isEmpty()) {
            int temp = stack.pop();
            ts.push(temp);
        }
        while(!ts.isEmpty()) {
            if(i < min)
                stack.push(ts.pop() + val);
            else stack.push(ts.pop());
            i++;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */