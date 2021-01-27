package com.sky.hiwise.algorithms.leetcode.graph.uf;

public class CouplesHoldingHands765 {
    /**
     * 765. 情侣牵手
     * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
     * 一次交换可选择任意两人，让他们站起来交换座位。
     * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
     * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
     * 示例 1:
     * 输入: row = [0, 2, 1, 3]
     * 输出: 1
     * 解释: 我们只需要交换row[1]和row[2]的位置即可。
     * 示例 2:
     * 输入: row = [3, 2, 0, 1]
     * 输出: 0
     * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
     * 说明:
     * len(row) 是偶数且数值在 [4, 60]范围内。
     * 可以保证row 是序列 0...len(row)-1 的一个全排列。
     */
    public int minSwapsCouples(int[] row) {
        UnionFind uf = new UnionFind(row.length / 2);
        for (int i = 0; i < row.length; i += 2) {
            uf.union(row[i] / 2, row[i + 1] / 2);
        }
        return uf.getRet();
    }

    class UnionFind {

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

        public int getRet() {
            int ret = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != i) {
                    ret++;
                }
            }
            return ret;
        }
    }

    /**
     * 解题思路
     * 此处撰写解题思路
     * 将每队情侣的编号(0,1)(2,3)(4,5)...除以2得到(0,0)(1,1)(2,2)...
     * 这样相同编号代表是一对情侣
     * 遍历人员，将每张沙发上的两个人员编号union一下，如果本来编号就相同，则表示两个人是一类
     * 若一张沙发上两个人编号不同，则将这两个人的编号并集到同一个类别中，由于这两个人都分别有自己的对象，所以在后续的遍历中另外两个对象和他们沙发上的另一个人都会被并到同一个集合中，最终会形成一个闭环，如(0,1)(1,3)(2,0)(3,2)
     * 每一个闭环若有k张沙发，则需要交换k-1次，而在一个闭环中只有根节点root满足parent[root] == root
     * 所以最后得到的parent数组中parent[i] != i的个数就是最终需要交换的次数
     * 作者：chuanchuan-3
     * 链接：https://leetcode-cn.com/problems/couples-holding-hands/solution/java-bing-cha-ji-by-chuanchuan-3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
