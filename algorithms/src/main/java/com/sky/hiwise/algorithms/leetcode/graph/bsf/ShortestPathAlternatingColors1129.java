package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class ShortestPathAlternatingColors1129 {

    /**
     * 1129. 颜色交替的最短路径
     * 在一个有向图中，节点分别标记为 0, 1, ..., n-1。这个图中的每条边不是红色就是蓝色，且存在自环或平行边。
     * red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的蓝色有向边。
     * 返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。
     * 示例 1：
     * 输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
     * 输出：[0,1,-1]
     * 示例 2：
     * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
     * 输出：[0,1,-1]
     * 示例 3：
     * 输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
     * 输出：[0,-1,-1]
     * 示例 4：
     * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
     * 输出：[0,1,2]
     * 示例 5：
     * 输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
     * 输出：[0,1,1]
     * 1 <= n <= 100
     * red_edges.length <= 400
     * blue_edges.length <= 400
     * red_edges[i].length == blue_edges[i].length == 2
     * 0 <= red_edges[i][j], blue_edges[i][j] < n
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-path-with-alternating-colors
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @param red_edges
     * @param blue_edges
     * @return
     */
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        TreeSet<Integer>[] redGraph = new TreeSet[n];
        TreeSet<Integer>[] blueGraph = new TreeSet[n];
        for (int i = 0; i < red_edges.length; i++) {
            if (redGraph[red_edges[i][0]] == null ) {
                redGraph[red_edges[i][0]] = new TreeSet<>();
            }
            redGraph[red_edges[i][0]].add(red_edges[i][1]);
        }
        for (int j = 0; j < blue_edges.length; j++) {
            if (blueGraph[blue_edges[j][0]] == null) {
                blueGraph[blue_edges[j][0]] = new TreeSet<>();
            }
            blueGraph[blue_edges[j][0]].add(blue_edges[j][1]);
        }

        //visited 0 red 1 blue
        boolean[][][] visited = new boolean[100][100][2];
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(0, 1));
        queue.add(new Node(0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        int step = 0;
        while (!queue.isEmpty()) {
            step ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                int curr = currNode.node;
                int currColor = currNode.color;
                if (currColor == 0) {
                    if (blueGraph[curr] == null) {
                        continue;
                    }
                   for (int nextBlue : blueGraph[curr]) {
                       if (!visited[curr][nextBlue][1]) {
                           visited[curr][nextBlue][1] = true;
                           ans[nextBlue] = Math.min(ans[nextBlue], step);
                           queue.add(new Node(nextBlue, 1));
                       }
                   }
                } else {
                    if (redGraph[curr] == null) {
                        continue;
                    }
                    for (int nextRed : redGraph[curr]) {
                        if (!visited[curr][nextRed][0]) {
                            visited[curr][nextRed][0] = true;
                            ans[nextRed] = Math.min(ans[nextRed], step);
                            queue.add(new Node(nextRed, 0));
                        }
                    }
                }
            }
        }
        ans[0] = 0;
        for (int i = 0; i < n; i++) {
            if (ans[i] == Integer.MAX_VALUE) {
                ans[i] = -1;
            }
        }
        return ans;
    }

    public class Node {
        private int node;
        private int color;  //0 red 1 blue
        public Node(int node, int color) {
            this.node = node;
            this.color = color;
        }
    }
    /**
     * BFS或者DFS均可.
     * 本题有两个关键点需要注意，一是颜色交替，意味着上一步的路径颜色与下一步的路径颜色一定不相同，
     * 二题中明确说明了，图中可能存在环，或者不同颜色的平行边（edge：blue:（0，1）,red(0,1)）为两条不同的边。
     * 颜色交替的解决办法，我们每次传递时，将上一次遍历的边的颜色信息带上，在下一次遍历时，选择不同的颜色的邻接节点,节点信息为（node,color).
     * 由于存在环和平行边，我们用数组visit[x][y][color] = true,代表从节点x到节点y的且颜色为color的边被访问过，防止重复访问。
     *
     * 作者：mike-meng
     * 链接：https://leetcode-cn.com/problems/shortest-path-with-alternating-colors/solution/bfs-by-mike-meng-4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
