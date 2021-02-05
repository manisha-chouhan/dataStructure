package com.datastructures.array;

import java.util.Arrays;
import java.util.HashSet;

public class FairSwap {
        public int[] fairCandySwap(int[] A, int[] B) {
            int sumA = Arrays.stream(A).sum();
            int sumB = Arrays.stream(B).sum();

            int middle = (sumA + sumB) / 2;

            if(middle == sumA && middle == sumB) return new int[]{0, 0};

            int[] result = new int[2];

            HashSet<Integer> set = new HashSet<>();
            if(sumA < middle) {
                for(int i = 0; i < B.length; i++) {
                    set.add(B[i]);
                }
                for(int i = 0; i < A.length; i++) {
                    int sum = middle - (sumA - A[i]);
                    if(set.contains(sum)) {
                        if(sumB - sum + A[i] == middle) {
                            result[0] = A[i];
                            result[1] = sum;
                            return result;
                        }
                    }
                }
            }
            else if(sumB < middle) {
                for(int i = 0; i < A.length; i++) {
                    set.add(A[i]);
                }
                for(int i = 0; i < B.length; i++) {
                    int sum = middle - (sumB - B[i]);
                    if(set.contains(sum)) {
                        if(sumA - sum + B[i] == middle) {
                            result[0] = sum;
                            result[1] = B[i];
                            return result;
                        }
                    }
                }
            }
            return result;
        }
}
