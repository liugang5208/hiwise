package com.sky.hiwise.algorithms.leetcode.graph.uf;

import java.util.*;
import java.util.stream.Collectors;

public class CheckExistEdgeLengthLimitedPaths1697 {
    /**
     * 1697. 检查边长度限制的路径是否存在
     * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
     * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
     * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
     * 示例 1：
     * 输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
     * 输出：[false,true]
     * 解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
     * 对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
     * 对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
     */
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int m = queries.length;
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        int[][] queryList = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                queryList[i][j] = queries[i][j];
            }
            queryList[i][3] = i;
        }
        Arrays.sort(queryList, Comparator.comparingInt(a -> a[2]));
        boolean[] ans = new boolean[m];
        UnionFind uf = new UnionFind(n);
        int idx = 0;
        for (int[] q : queryList) {
            while (idx < edgeList.length && edgeList[idx][2] < q[2]) {
                uf.union(edgeList[idx][0], edgeList[idx][1]);
                idx++;
            }
            ans[q[3]] = uf.isConnected(q[0], q[1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] edgeList = new int[][]{{0,1,2},{1,2,4},{2,0,8},{1,0,16}};
        int[][] queries = new int[][]{{0,1,2},{0,2,5}};
        boolean[] ans = (new CheckExistEdgeLengthLimitedPaths1697()).distanceLimitedPathsExist(n, edgeList, queries);
        for (boolean a : ans) {
            System.out.println(a);
        }
    }

    /**
     * 但是如果转变成离线的思想，就比较简单了。因为问题中每个 query 是要判断是否存在从 p(i) 到 q(i) 所有边都小于 limit(i) 的路径。
     * 如果 limit(i) 是从小到大排列的，我们就可以顺次在图上，每次把小于当前 limit(i) 的边添加上，然后使用并查集查看当前 query 关心的 p(i) 到 q(i) 是否连通。
     * 这样做的核心在于，我们一边处理 query，一边处理边，不是一次性的把所有的边都考虑进来，而是根据 query 的 limit 从小到大的限制，从小到大依次考虑边。
     * 注意，这个思路需要我们首先对整个 query 数组排序，之后再处理，所以需要收集到所有 query 信息以后再执行，它是一个离线算法。
     * 作者：liuyubobobo
     * 链接：https://leetcode-cn.com/problems/checking-existence-of-edge-length-limited-paths/solution/jie-zhe-ge-wen-ti-ke-pu-yi-xia-shi-yao-j-pn1b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
