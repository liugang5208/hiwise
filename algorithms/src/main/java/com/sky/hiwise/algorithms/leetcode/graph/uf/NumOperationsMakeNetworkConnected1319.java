package com.sky.hiwise.algorithms.leetcode.graph.uf;

public class NumOperationsMakeNetworkConnected1319 {
    /**
     * 1319. 连通网络的操作次数
     * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
     * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
     * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
     */
    public int makeConnected(int n, int[][] connections) {
        UF uf = new UF(n);
        int cnt = 0;
        int num = n;
        for (int[] c : connections) {
            int a = c[0];
            int b = c[1];
            if (uf.find(a) == uf.find(b)) {
                cnt++;
                continue;
            }
            uf.union(a, b);
            num--;
        }
        int cnt2 = num - 1;
        if (cnt < cnt2) {
            return -1;
        }
        return cnt2;
    }

    /**
     * 思路
     * 看到连通两个字样，十有八九就是并查集类型的题目，此时我们就应该拿出精心准备的并查集模板出来（结尾附赠）。
     * 我们将互相连通的电脑压缩成一个点，若最后存在 N 个点，通过观察可以发现只需要 N - 1 条线就可以进行相连。
     * 那接下来的问题就是如何去寻找这 N - 1 条线。
     * 考虑每一条线，如果线的两头已经连通（find(p) == find(q)），则这条线是多余的，可以拿去当做 N - 1 条线的其中一条。
     * 作者：hlxing
     * 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/solution/qing-xi-tu-jie-jing-zhi-de-bing-cha-ji-mo-ban-by-h/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class UF {
        private int[] parent;
        public UF(int n) {
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

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                parent[pRoot] = qRoot;
            }
        }
    }
}
