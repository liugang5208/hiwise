package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitAllNodes847 {
    /**
     * 847. 访问所有节点的最短路径
     * 给出 graph 为有 N 个节点（编号为 0, 1, 2, ..., N-1）的无向连通图。
     * graph.length = N，且只有节点 i 和 j 连通时，j != i 在列表 graph[i] 中恰好出现一次。
     * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
     * 示例 1：
     * 输入：[[1,2,3],[0],[0],[0]]
     * 输出：4
     * 解释：一个可能的路径为 [1,0,2,0,3]
     * 示例 2：
     * 输入：[[1],[0,2,4],[1,3,4],[2],[1,2]]
     * 输出：4
     * 解释：一个可能的路径为 [0,1,4,2,3]
     * 提示：
     * 1 <= graph.length <= 12
     * 0 <= graph[i].length < graph.length
     */
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<Node> queue = new LinkedList<>();
        //记录cover状态  访问head节点时的距离
        int[][] dist = new int[1<<n][n];
        for (int[] row : dist) {
            Arrays.fill(row, n * n);
        }
        for (int i = 0; i < n; i++) {
            queue.add(new Node(1 << i, i));
            dist[1<<i][i] = 0;
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int d = dist[node.cover][node.head];
            if (node.cover == (1<<n) - 1) {
                return d;
            }
            for (int child : graph[node.head]) {
                int childCover = node.cover | (1 << child);
                if (d + 1 < dist[childCover][child]){
                    dist[childCover][child] = d + 1;
                    queue.add(new Node(childCover, child));
                }
            }
        }
        return -1;
    }

    class Node {
        public int cover;
        public int head;
        public Node(int cover, int head) {
            this.cover = cover;
            this.head = head;
        }
    }
    /**
     * 方法一：广度优先搜索【通过】
     * 思路
     * 路径 state 表示当前节点和已访问节点的子集。此问题可以简化为 state 的最短路径问题，那么就可以使用广度优先搜索解决。
     * 算法
     * cover 表示一条路径上访问过的节点集合，head 表示当前节点。在 cover 中使用比特位表示节点的访问情况，如果 cover 的第 k 个比特位是 1，表示该路径经过了第 k 个节点。
     * 对当前 state = (cover, head) 使用广度优先搜索，从当前节点 head 出发到达每个邻接点 child 的新路径为 (cover | (1 << child), child)。
     * 根据广度优先搜索可知，如果找到一个 state 包含了全部顶点，那么该 state 一定代表最短路径的长度。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes/solution/fang-wen-suo-you-jie-dian-de-zui-duan-lu-jing-by-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
