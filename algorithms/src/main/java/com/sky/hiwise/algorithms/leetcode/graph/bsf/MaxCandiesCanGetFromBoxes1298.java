package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MaxCandiesCanGetFromBoxes1298 {
    /**
     * 1298. 你能从盒子里获得的最大糖果数
     * 给你 n 个盒子，每个盒子的格式为 [status, candies, keys, containedBoxes] ，其中：
     * 状态字 status[i]：整数，如果 box[i] 是开的，那么是 1 ，否则是 0 。
     * 糖果数 candies[i]: 整数，表示 box[i] 中糖果的数目。
     * 钥匙 keys[i]：数组，表示你打开 box[i] 后，可以得到一些盒子的钥匙，每个元素分别为该钥匙对应盒子的下标。
     * 内含的盒子 containedBoxes[i]：整数，表示放在 box[i] 里的盒子所对应的下标。
     * 给你一个 initialBoxes 数组，表示你现在得到的盒子，你可以获得里面的糖果，也可以用盒子里的钥匙打开新的盒子，还可以继续探索从这个盒子里找到的其他盒子。
     * 请你按照上述规则，返回可以获得糖果的 最大数目 。
     * 示例 1：
     * 输入：status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
     * 输出：16
     * 解释：
     * 一开始你有盒子 0 。你将获得它里面的 7 个糖果和盒子 1 和 2。
     * 盒子 1 目前状态是关闭的，而且你还没有对应它的钥匙。所以你将会打开盒子 2 ，并得到里面的 4 个糖果和盒子 1 的钥匙。
     * 在盒子 1 中，你会获得 5 个糖果和盒子 3 ，但是你没法获得盒子 3 的钥匙所以盒子 3 会保持关闭状态。
     * 你总共可以获得的糖果数目 = 7 + 4 + 5 = 16 个。
     * 示例 2：
     * 输入：status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
     * 输出：6
     * 解释：
     * 你一开始拥有盒子 0 。打开它你可以找到盒子 1,2,3,4,5 和它们对应的钥匙。
     * 打开这些盒子，你将获得所有盒子的糖果，所以总糖果数为 6 个。
     */
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        Set<Integer> haveBoxes = new HashSet<>();
        Set<Integer> haveKeys = new HashSet<>();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < initialBoxes.length; i++) {
            int idx = initialBoxes[i];
            haveBoxes.add(idx);
            if (status[idx] == 1) {
                queue.add(idx);
                visited[idx] = true;
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            ans += candies[curr];
            int[] currKeys = keys[curr];
            int[] currBoxes = containedBoxes[curr];
            for (int key : currKeys) {
                haveKeys.add(key);
                if (!visited[key] && haveBoxes.contains(key)) {
                    queue.add(key);
                    visited[key] = true;
                }
            }
            for (int box : currBoxes) {
                haveBoxes.add(box);
                if (!visited[box] && (haveKeys.contains(box) || status[box] == 1)) {
                    queue.add(box);
                    visited[box] = true;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] status = new int[]{1,0,1,0};
        int[] candies = new int[]{7,5,4,100};
        int[][] keys = new int[][]{{},{},{1},{}};
        int[][] containedBoxes = new int[][]{{1,2},{3},{},{}};
        int[] initialBoxes = new int[]{0};
        System.out.println((new MaxCandiesCanGetFromBoxes1298()).maxCandies(status, candies, keys, containedBoxes, initialBoxes));
    }
    /**
     * 思路
     * 通过广度优先搜索方式来模拟拆盒子的过程。使用队列装入可以被拆开的盒子（状态打开 or 拥有对应钥匙），
     * 每次从队列取出一个盒子，并遍历这个盒子里面的内含盒子以及钥匙，遍历的情况包括：
     * 内含盒子没有被拆开过，并且有对应钥匙或状态打开，则把它加入队列
     * 钥匙所对应的的盒子存在（我们拥有这个盒子），并且盒子没有被拆开，则把它加入队列
     * 作者：hlxing
     * 链接：https://leetcode-cn.com/problems/maximum-candies-you-can-get-from-boxes/solution/qing-xi-jian-dan-bfs-mo-ni-by-hlxing/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
