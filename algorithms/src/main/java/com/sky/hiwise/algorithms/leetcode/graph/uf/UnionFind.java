package com.sky.hiwise.algorithms.leetcode.graph.uf;

public class UnionFind {

    private int[] parent;
    public  UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            parent[pRoot] = qRoot;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int getConnectedCount() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }
}
