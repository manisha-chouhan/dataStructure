package com.datastructures.design;

import java.util.HashMap;
import java.util.Stack;

public class BrowserHistory {
    HashMap<String, String> forward;
    Stack<String> backward;
    String curr = null;
    public BrowserHistory(String homepage) {
        forward = new HashMap<>();
        backward = new Stack<>();
        backward.push(homepage);
        curr = homepage;
    }

    public void visit(String url) {
        backward.push(url);
        forward.put(curr, url);
        curr = url;
    }

    public String back(int steps) {
        int i = 0;
        while(!backward.isEmpty() && i < steps) {
            curr = backward.pop();
            i++;
        }

        if(!backward.isEmpty()) curr = backward.peek();
        if(backward.isEmpty()) backward.push(curr);
        return curr;
    }

    public String forward(int steps) {
        int i = 0;
        while(i < steps && forward.containsKey(curr)) {
            curr = forward.get(curr);
            backward.push(curr);
            i++;
        }
        if(!backward.isEmpty() && !curr.equals(backward.peek())) backward.push(curr);
        if(backward.isEmpty()) backward.push(curr);
        return curr;
    }
}
