package com.datastructures.array;

public class TestClass {
    public static void main(String[] args) {
        double m = 6/4;
        System.out.println(m);
    }

    public int[] replaceElements(int[] arr) {
        int[] res = new int[arr.length];
        int max = -1;
        for(int i = arr.length - 1; i >= 0; i--) {
            res[i] = max;
            max = Math.max(max, arr[i]);
        }
        return res;
    }

    public boolean checkStraightLine(int[][] coordinates) {
        double m = Double.MIN_VALUE;
        for(int i = 1; i < coordinates.length; i++) {
            int[] prev = coordinates[i - 1];
            int[] curr = coordinates[i];
            double _m =  (curr[0] - prev[0] == 0) ? Double.MAX_VALUE : (double)(curr[1] - prev[1])/(curr[0] - prev[0]);
            if(m == Double.MIN_VALUE) m = _m;
            if(_m != m)return false;
        }
        return true;
    }
}
