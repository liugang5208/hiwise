package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinTimeCollectApplesInTree1443 {
    /**
     * 1443. 收集树上所有苹果的最少时间
     * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
     * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。除此以外，还有一个布尔数组 hasApple ，其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
     * 示例 1：
     * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
     * 输出：8
     * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int ans = 0;
        for(int i = edges.length - 1; i > 0; i--) {
            if (hasApple.get(edges[i][1])) {
                hasApple.set(edges[i][0], true);
            }
        }
        for (int i = 0; i < edges.length; i++) {
            if (hasApple.get(edges[i][1])) {
                ans += 2;
            }
        }
        return ans;
    }

    /**
     * 解题思路
     * 条件分析：
     * edges.length == n-1
     * 这个条件很重要，说明对于任意一个to节点，有且仅有一个from节点。所以根（即节点0）到任意一个节点的路径，有且仅有一条。
     * 也就是说，从任意一个苹果节点，有且仅有一条路径到根节点。
     * 解题思路也就有了：1. 从苹果节点找到根节点，经过的距离*2 （因为还得加上从根节点到苹果节点的距离），经过的节点放到visted数组里。
     * 2. 对下一个苹果节点，也往根节点寻找，只要经过visited组里任意一个节点（不必到根节点），即可。
     *
     * 使用纯数组，不用hash，所以也更加高效，更方便翻译成其他没有内置hash的语言。
     *
     * 这个提交，最初是通过了官方的检查。 应该是后来官方添加了新的测试用例，提交会失败， 多谢@user0486v指出。起初这个思路，现在重新思考了一下，是有些漏洞。
     * 主要是在构建reverseEdges的时候，没有考虑到1对多以及edge的from,to不确定的情况， 想当然的认为了from就是距离0点近。先予以更改。多谢 于2020-05-15
     *
     * 附：后来官方新加的case，更改后的程序已经通过测试。
     * 4
     * [[0,2],[0,3],[1,2]]
     * [false,true,false,false]
     *
     * 作者：geguanting
     * 链接：https://leetcode-cn.com/problems/minimum-time-to-collect-all-apples-in-a-tree/solution/dfsshen-ru-qian-chu-by-geguanting/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    List<List<Integer>> nodeMap = new ArrayList<>();
    int[] reverseEdges;
    int ans = 0;
    boolean[] visited;
    public int minTime1(int n, int[][] edges, List<Boolean> hasApple) {
        for (int i = 0; i < n; i++) {
            nodeMap.add(new ArrayList<>());
        }
        for (int[] edge : edges){
            nodeMap.get(edge[0]).add(edge[1]);
            nodeMap.get(edge[1]).add(edge[0]);
        }
        reverseEdges = new int[n];
        Arrays.fill(reverseEdges, -1);
        buildReverseEdges(0);
        visited = new boolean[n];
        visited[0] = true;
        for (int i = 0; i < n; i++) {
            if (hasApple.get(i)) {
                dfs(i);
            }
        }
        return ans * 2;
    }

    public void dfs(int to) {
        if (!visited[to]) {
            visited[to] = true;
            ans++;
            dfs(reverseEdges[to]);
        }
    }

    public void buildReverseEdges(int val) {
        for (int next : nodeMap.get(val)) {
            if (next != 0 && reverseEdges[next] == -1) {
                reverseEdges[next] = val;
                buildReverseEdges(next);
            }
        }
    }

}

