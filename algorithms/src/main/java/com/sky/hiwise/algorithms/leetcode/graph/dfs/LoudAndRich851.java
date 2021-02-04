package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;

public class LoudAndRich851 {

    /**
     * 851. 喧闹和富有
     * 在一组 N 个人（编号为 0, 1, 2, ..., N-1）中，每个人都有不同数目的钱，以及不同程度的安静（quietness）。
     * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
     * 如果能够肯定 person x 比 person y 更有钱的话，我们会说 richer[i] = [x, y] 。注意 richer 可能只是有效观察的一个子集。
     * 另外，如果 person x 的安静程度为 q ，我们会说 quiet[x] = q 。
     * 现在，返回答案 answer ，其中 answer[x] = y 的前提是，在所有拥有的钱不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
     * 示例：
     * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
     * 输出：[5,5,2,5,4,5,6,7]
     * 解释：
     * answer[0] = 5，
     * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
     * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
     * 但是目前还不清楚他是否比 person 0 更有钱。
     * answer[7] = 7，
     * 在所有拥有的钱肯定不少于 person 7 的人中(这可能包括 person 3，4，5，6 以及 7)，
     * 最安静(有较低安静值 quiet[x])的人是 person 7。
     * 其他的答案也可以用类似的推理来解释。
     * 提示：
     * 1 <= quiet.length = N <= 500
     * 0 <= quiet[i] < N，所有 quiet[i] 都不相同。
     * 0 <= richer.length <= N * (N-1) / 2
     * 0 <= richer[i][j] < N
     * richer[i][0] != richer[i][1]
     * richer[i] 都是不同的。
     * 对 richer 的观察在逻辑上是一致的。
     */
    ArrayList<Integer>[] graph;
    int[] quiet;
    int[] ans;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        this.quiet = quiet;
        ans = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        //person x 比 person y 更有钱的话，我们会说 richer[i] = [x, y]
        //如果 x 比 y 富有，就认为在有向图中存在边 y -> x 。
       for (int[] edges : richer) {
           graph[edges[1]].add(edges[0]);
       }

       Arrays.fill(ans, -1);
       for (int node = 0; node < n; node++) {
           dfs(node);
       }
       return ans;
    }

    public int dfs(int node) {
        if (ans[node] == -1) {
            ans[node] = node;
            for (int next : graph[node]) {
                int candidate = dfs(next);
                if (quiet[candidate] < quiet[ans[node]]) {
                    ans[node] = candidate;
                }

            }
        }
        return ans[node];
    }

    /**
     * 方法：缓存深度优先搜索法
     * 思路
     * 如果 y 比 x 富有，就认为在有向图中存在边 x -> y 。
     * 对每个 x（也就是每个人），我们都希望最安静的人就在 x 的子树中。
     * 算法
     * 构建上面所描述的图，并且 dfs(person) 是 person 的子树上最安静的人。注意，因为语句在逻辑上是一致的，所以图必须是有向无环图（即，DAG）—— 任意一条边都有方向，且不存在环路的图。
     * 现在 dfs(person) 既可以是 person 本身，也可以是 min(dfs(child)) 。也就是说，子树中最安静的人可以是 person 本身，或者是 person 的子结点的某个子树中最安静的人。
     * 当执行图的 后序遍历 时，我们可以将 dfs(person) 的值缓存为 answer[person] 。这样，我们就不会重复工作。该技巧有助于将算法的时间复杂度从平方阶降低到线性阶。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/loud-and-rich/solution/xuan-nao-he-fu-you-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
