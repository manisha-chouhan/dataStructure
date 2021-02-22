package com.datastructures.math;

public class Medium {

    //https://leetcode.com/problems/powx-n/
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        double temp = myPow(x, n/2);
        temp = temp *temp;
        if(n%2 != 0){
            if(n > 0)
                temp = x*temp;
            else temp = temp/x;
        }
        return temp;
    }
}
