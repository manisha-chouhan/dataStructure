package com.datastructures.array;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOFStudentsHavingLunch {
        public int countStudents(int[] students, int[] sandwiches) {
            Queue<Integer> que = new LinkedList<>();
            for (int i : students) {
                que.offer(i);
            }
            for (int i = 0; i < sandwiches.length; i++) {
                if (que.peek() == sandwiches[i]) {
                    que.poll();
                    continue;
                }
                int size = que.size();
                int count = 0;
                boolean found = false;

                while (count < size) {
                    if (que.peek() == sandwiches[i]) {
                        que.poll();
                        found = true;
                        break;
                    }
                    int student = que.poll();
                    que.offer(student);
                    count++;
                }
                if (!found) break;
            }
            return que.size();
        }
}
