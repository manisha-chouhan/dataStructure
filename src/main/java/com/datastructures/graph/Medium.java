package com.datastructures.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Medium {

    int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public void solve(char[][] board) {
        //traverse the borders\
        int n = board.length;
        if(n == 0) return;
        int m = board[0].length;
        int i = 0;
        int j = 0;
        HashSet<String> visited = new HashSet<>();

        //traverse first row
        for(j = 0; j < m; j++) {
            if(board[i][j] == 'O') {
                bfs(i, j, board, visited);
            }
        }
        i = 0;
        j = 0;
        //first column
        for(i = 0; i < n; i++) {
            if(board[i][j] == 'O') {
                bfs(i, j, board, visited);
            }
        }
        i = n - 1;
        //last row
        for(j = 1; j < m; j++) {
            if(board[i][j] == 'O') {
                bfs(i, j, board, visited);
            }
        }

        //last column
        j = m - 1;
        for(i = 1; i < n; i++) {
            if(board[i][j] == 'O') {
                bfs(i, j, board, visited);
            }
        }
        for(i = 0; i < n; i++) {
            for(j = 0; j < m; j++) {
                if(board[i][j] == 'O')board[i][j] = 'X';
                else if(board[i][j] == '-') board[i][j] = 'O';
            }
        }
        return;
    }

    public void bfs(int i, int  j, char[][] board, HashSet<String> visited) {
        if(!isSafe(i, j, board, visited)) return;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j});
        board[i][j] = '-';
        visited.add("row : " + i + " col : " + j);
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                int[] temp = q.poll();
                size--;
                for(int k = 0; k < 4; k++) {
                    int x = temp[0] + dir[k][0];
                    int y = temp[1] + dir[k][1];
                    if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) continue;
                    if(board[x][y] == 'O') {
                        String str = "row : " + x + " col : " + y;
                        if(visited.contains(str)) continue;
                        board[x][y] = '-';
                        q.offer(new int[] {x,y});
                        visited.add(str);
                    }
                }
            }
        }
    }

    public boolean isSafe(int i, int j, char[][] board, HashSet<String> visited) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X') return false;
        if(visited.contains("row : " + i + " col : " + j)) return false;
        return true;
    }

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE;
        int level = 1;
        int res = 0;
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            int sum = 0;

            while(size > 0) {
                TreeNode node = q.poll();
                size--;
                sum += node.val;
                if(node.left != null) {
                    q.offer(node.left);
                }
                if(node.right != null) {
                    q.offer(node.right);
                }
            }
            if(sum > max) {
                max = sum;
                res = level;
            }
            level++;
        }
        return res;
    }

    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    public Node connect(Node root) {
        if(root == null)return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            Node prev = null;
            int size = q.size();
            while(size > 0) {
                Node temp = q.poll();
                size--;
                if(temp.left != null)q.offer(temp.left);
                if(temp.right != null)q.offer(temp.right);
                if(prev != null) {
                    prev.next = temp;
                }
                prev = temp;
            }
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    //https://leetcode.com/problems/open-the-lock/
    public int openLock(String[] deadends, String target) {
        HashSet<String> _deadends = new HashSet<>(Arrays.asList(deadends));
        HashSet<String> visited = new HashSet<>();
        String curr = "0000";
        if(_deadends.contains(curr))return -1;

        Queue<String> q = new LinkedList<>();
        q.offer(curr);
        visited.add(curr);
        int steps = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                curr = q.poll();
                if(curr.equals(target)) return steps;
                size--;
                for(int i = 0; i < 4; i++) {
                    char ch = curr.charAt(i);
                    String inc = curr.substring(0, i) + (ch == '9' ? "0" : (char)(ch + 1)) + curr.substring(i + 1);
                    String dec = curr.substring(0, i) + (ch == '0' ? "9" : (char)(ch - 1)) + curr.substring(i + 1);
                    if(!_deadends.contains(inc) && !visited.contains(inc)) {
                        q.offer(inc);
                        visited.add(inc);
                    }
                    if(!_deadends.contains(dec) && !visited.contains(dec)) {
                        q.offer(dec);
                        visited.add(dec);
                    }

                }
            }
            steps++;
        }
        return -1;
    }

    int[][] direc = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    //https://leetcode.com/problems/path-with-minimum-effort/
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] efforts = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                efforts[i][j] = Integer.MAX_VALUE;
            }
        }

        efforts[0][0] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        int result = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                int[] curr = q.poll();
                if(curr[0] == n - 1 && curr[1] == m - 1) result = Math.min(result, efforts[curr[0]][curr[1]]);
                size--;
                for(int i = 0; i < 4; i++) {
                    int x = curr[0] + direc[i][0];
                    int y = curr[1] + direc[i][1];
                    if(!isSafe(x, y, n, m)) continue;
                    int _effort = Math.max(efforts[curr[0]][curr[1]], Math.abs(heights[x][y] - heights[curr[0]][curr[1]]));
                    if(efforts[x][y] > _effort) {
                        efforts[x][y] = _effort;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return result;
    }

    public boolean isSafe(int x, int y, int n, int m) {
        if(x < 0 || x >= n || y < 0 || y >= m) return false;
        return true;
    }

}
