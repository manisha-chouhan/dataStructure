package com.datastructures.unionfind;

public class NetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < (n -1)) return -1;
        int component = n;
        UnionFind uf = new UnionFind(n);

        for(int[] connection : connections) {
            int p1 = uf.find(connection[0]);
            int p2 = uf.find(connection[1]);

            if(p1 != p2) {
                uf.union(p1, p2);
                component--;
            }
        }
        return component - 1;
    }

    class UnionFind {
        int[] parent;
        int[] size;
        int n;

        UnionFind(int n) {
            this.n = n;
            parent = new int[n];
            size = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if(parent[x] == x) return x;
            return find(parent[x]);
        }

        public void union(int x, int y) {
            int a = find(x);
            int b = find(y);

            if(a == b)return;
            if(size[a] <= size[b]) {
                parent[b] = a;
                size[a] += size[b];
            }
            else {
                parent[a] = b;
                size[b] += size[a];
            }

        }

    }
}
