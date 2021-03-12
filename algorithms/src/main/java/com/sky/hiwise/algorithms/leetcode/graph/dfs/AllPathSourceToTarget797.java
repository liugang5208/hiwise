package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class AllPathSourceToTarget797 {
    /**
     * 797. 所有可能的路径
     * 给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
     * 二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点（译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ）空就是没有下一个结点了。
     * 示例 1：
     * 输入：graph = [[1,2],[3],[3],[]]
     * 输出：[[0,1,3],[0,2,3]]
     * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
     * 示例 2：
     * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
     * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
     * 示例 3：
     * 输入：graph = [[1],[]]
     * 输出：[[0,1]]
     * 示例 4：
     * 输入：graph = [[1,2,3],[2],[3],[]]
     * 输出：[[0,1,2,3],[0,2,3],[0,3]]
     * 示例 5：
     * 输入：graph = [[1,3],[2],[3],[]]
     * 输出：[[0,1,2,3],[0,3]]
     * 提示：
     * 结点的数量会在范围 [2, 15] 内。
     * 你可以把路径以任意顺序输出，但在路径内的结点的顺序必须保证。
     */
    List<List<Integer>> ans = new ArrayList<>();
    int N;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        N = graph.length;
        if (N == 0) {
            return ans;
        }
        List<Integer> temp =  new ArrayList<>();
        temp.add(0);
        dfs(graph, 0, temp);
        return ans;
    }

    public void dfs(int[][] graph, int index, List<Integer> temp) {
        if (index == N - 1) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int next : graph[index]) {
            temp.add(next);
            dfs(graph, next, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{1,2},{3},{3},{}};
        System.out.println((new AllPathSourceToTarget797()).allPathsSourceTarget(graph));
    }

    /**
     题目中给出的图是有向无环的，那么我们可以通过深度优先搜索的方法，递归求解出所有的路径。
     设图中有 N 个节点，在搜索时，如果我们到达了节点 N - 1，那么此时的路径就为 {N - 1}；
     否则如果我们到达了其它的节点 node，那么路径就为 {node} 加上 {所有从 nei 到 N - 1} 的路径集合，其中 nei 是 node 直接相邻的节点。
     作者：LeetCode
     链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target/solution/suo-you-ke-neng-de-lu-jing-by-leetcode/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
