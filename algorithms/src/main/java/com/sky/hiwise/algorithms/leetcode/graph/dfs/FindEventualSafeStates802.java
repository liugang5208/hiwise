package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates802 {
    /**
     * 802. 找到最终的安全状态
     * 在有向图中, 我们从某个节点和每个转向处开始, 沿着图的有向边走。 如果我们到达的节点是终点 (即它没有连出的有向边), 我们停止。
     * 现在, 如果我们最后能走到终点，那么我们的起始节点是最终安全的。 更具体地说, 存在一个自然数 K,  无论选择从哪里开始行走, 我们走了不到 K 步后必能停止在一个终点。
     * 哪些节点最终是安全的？ 结果返回一个有序的数组。
     * 该有向图有 N 个节点，标签为 0, 1, ..., N-1, 其中 N 是 graph 的节点数.  图以以下的形式给出: graph[i] 是节点 j 的一个列表，满足 (i, j) 是图的一条有向边。
     * 示例：
     * 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
     * 输出：[2,4,5,6]
     * 这里是上图的示意图。
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (dfs(i, colors, graph)) {
                ans.add(i);
            }
        }
        return ans;
    }

    // colors: WHITE 0, GRAY 1, BLACK 2;
    public boolean dfs(int idx, int[] colors, int[][] graph) {
        if (colors[idx] > 0) {
            return colors[idx] == 2;
        }
        colors[idx] = 1;
        for (int i : graph[idx]) {
            if (colors[i] == 2) {
                continue;
            } else if (colors[i] == 1 || !dfs(i, colors, graph)) {
                return false;
            }
        }
        colors[idx] = 2;
        return true;
    }
    /**
     * 方法二：深度优先搜索
     * 我们同样可以使用深度优先搜索的方法判断图中的每个节点是否能走到环中。对于每个节点，我们有三种染色的方法：白色表示该节点还没有被访问过；灰色表示该节点在栈中（这一轮搜索中被访问过）或者在环中；黑色表示该节点的所有相连的节点都被访问过，且该节点不在环中。
     * 当我们第一次访问一个节点时，我们把它从白色变成灰色，并继续搜索与它相连的节点。如果在搜索过程中我们遇到一个灰色的节点，那么说明找到了一个环，此时退出搜索，所有的灰色节点保持不变（即从任意一个灰色节点开始，都能走到环中），如果搜索过程中，我们没有遇到灰色的节点，那么在回溯到当前节点时，我们把它从灰色变成黑色，即表示它是一个安全的节点。
     * 链接：https://leetcode-cn.com/problems/find-eventual-safe-states/solution/zhao-dao-zui-zhong-de-an-quan-zhuang-tai-by-leetco/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
