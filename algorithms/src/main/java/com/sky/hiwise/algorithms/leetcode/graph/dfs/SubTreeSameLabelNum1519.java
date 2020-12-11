package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubTreeSameLabelNum1519 {
    /**
     * 1519. 子树中标签相同的节点数
     * 给你一棵树（即，一个连通的无环无向图），这棵树由编号从 0  到 n - 1 的 n 个节点组成，且恰好有 n - 1 条 edges 。
     * 树的根节点为节点 0 ，树上的每一个节点都有一个标签，也就是字符串 labels 中的一个小写字符（编号为 i 的 节点的标签就是 labels[i] ）
     * 边数组 edges 以 edges[i] = [ai, bi] 的形式给出，该格式表示节点 ai 和 bi 之间存在一条边。
     * 返回一个大小为 n 的数组，其中 ans[i] 表示第 i 个节点的子树中与节点 i 标签相同的节点数。
     * 树 T 中的子树是由 T 中的某个节点及其所有后代节点组成的树。
     * 示例 1：
     * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
     * 输出：[2,1,1,1,1,1,1]
     * 解释：节点 0 的标签为 'a' ，以 'a' 为根节点的子树中，节点 2 的标签也是 'a' ，因此答案为 2 。注意树中的每个节点都是这棵子树的一部分。
     * 节点 1 的标签为 'b' ，节点 1 的子树包含节点 1、4 和 5，但是节点 4、5 的标签与节点 1 不同，故而答案为 1（即，该节点本身）。
     */
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, List<Integer>> edgesMap = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            List<Integer> formList = edgesMap.getOrDefault(from, new ArrayList<Integer>());
            List<Integer> toList = edgesMap.getOrDefault(to, new ArrayList<Integer>());
            formList.add(to);
            toList.add(from);
            edgesMap.put(from, formList);
            edgesMap.put(to, toList);
        }
        int[] counts = new int[n];
        boolean[] visited = new boolean[n];
        dfs(0, counts, visited, edgesMap, labels);
        return counts;
    }

    public int[] dfs(int idx, int[] counts, boolean[] visited, Map<Integer, List<Integer>> edgesMap, String labels) {
        visited[idx] = true;
        int[] currCounts = new int[26];
        int currCharIdx = labels.charAt(idx) - 'a';
        currCounts[currCharIdx]++;
        for (Integer next : edgesMap.get(idx)) {
            if (!visited[next]) {
                int[] childCount = dfs(next, counts, visited, edgesMap, labels);
                for (int i = 0; i < 26; i++) {
                    currCounts[i] += childCount[i];
                }
            }
        }
        counts[idx] = currCounts[currCharIdx];
        return currCounts;
    }
    /**
     * 方法一：深度优先搜索
     * 这道题中，树的表示方法是一个连通的无环无向图。树中包含编号从 00 到 n-1n−1 的 nn 个节点，其中根节点为节点 00，以及 n-1n−1 条无向边。由于给出的边是无向的，因此不能直接从边的节点先后顺序判断父节点和子节点。
     *
     * 虽然给出的边是无向的，但是由于根节点已知，因此从根节点开始搜索，对每个访问到的节点标记为已访问，即可根据节点是否被访问过的信息知道相邻节点的父子关系。除了打访问标记外，还有一个办法可以解决这个问题，即在深度优先搜索的时候，把父节点当参数传入，即 depthFirstSearch(now, pre, ...) 的形式，其中 now 为当前节点，pre 为它的父节点，这样当邻接表中存的是无向图时，我们只要在拓展节点的时候判断 now 拓展到的节点等不等于 pre，即可避免再次拓展到父节点。下面 Java 代码中，给出前者的实现；C++ 和 Python 代码中，给出后者的实现。
     *
     * 这道题需要求出每个节点的子树中与该节点标签相同的节点数，即该节点的子树中，该节点标签出现的次数。由于一个节点的子树中可能包含任意的标签，因此需要对每个节点记录该节点的子树中的所有标签的出现次数。又由于标签一定是小写字母，因此只需要对每个节点维护一个长度为 2626 的数组即可。
     *
     * 显然，一个节点的子树中的每个标签出现的次数，由该节点的左右子树中的每个标签出现的次数以及该节点自身的标签决定，因此可以使用深度优先搜索，递归地计算每个节点的子树中的每个标签出现的次数。
     *
     * 当得到一个节点的子树中的每个标签出现的次数之后，即可根据该节点的标签，得到子树中与该节点标签相同的节点数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/solution/zi-shu-zhong-biao-qian-xiang-tong-de-jie-dian-sh-3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
