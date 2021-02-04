package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.*;

public class BusRoutes815 {
    /**
     * 815. 公交路线
     * 我们有一系列公交路线。每一条路线 routes[i] 上都有一辆公交车在上面循环行驶。例如，有一条路线 routes[0] = [1, 5, 7]，表示第一辆 (下标为0) 公交车会一直按照 1->5->7->1->5->7->1->... 的车站路线行驶。
     * 假设我们从 S 车站开始（初始时不在公交车上），要去往 T 站。 期间仅可乘坐公交车，求出最少乘坐的公交车数量。返回 -1 表示不可能到达终点车站。
     * 示例：
     * 输入：
     * routes = [[1, 2, 7], [3, 6, 7]]
     * S = 1
     * T = 6
     * 输出：2
     * 解释：
     * 最优策略是先乘坐第一辆公交车到达车站 7, 然后换乘第二辆公交车到车站 6。
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        int n = routes.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList<>());
        }

        //通过判断定点是否连通（站点是否相交） 构建图
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        Set<Integer> seen = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                queue.add(new Node(i, 0));
                seen.add(i);
            }
            if (Arrays.binarySearch(routes[i], T) >= 0) {
                targets.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (targets.contains(curr.idx)) {
                return curr.depth + 1;
            }
            for (int next : graph.get(curr.idx)) {
                if (!seen.contains(next)) {
                    seen.add(next);
                    queue.add(new Node(next, curr.depth + 1));
                }
            }
        }
        return -1;
    }

    class Node {
        public int idx;
        public int depth;
        public Node(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }

    private boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while(i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                return true;
            } else if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }
    /**
     * 广度优先搜索：
     * 我们将每一条公交路线（而不是每一个车站）看成图中的一个点，如果两条公交路线有交集，那么它们在图中对应的点之间就有一条边。
     * 此外，起点站 S 和终点站 T 也分别是图中的一个点，如果一条公交路线包含了 S 或 T，那么也需要和 S 或 T 对应的点连一条边。
     * 此时，在这个图上从 S 到 T 的最短路径长度即为答案，我们可以用广度优先搜索来找出最短路径。
     * 在计算两条公交路线是否有交集时，可以用的方法有很多种。例如将公交路线放在集合中，检查两个集合的交集是否为空；
     * 或者将公交路线中的车站进行递增排序，并使用双指针的方法检查是否有相同的车站。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/bus-routes/solution/gong-jiao-lu-xian-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
