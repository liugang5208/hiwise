package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinNumReachAllNodes1557 {

    /**
     * 1557. 可以到达所有点的最少点数目
     * 给你一个 有向无环图 ， n 个节点编号为 0 到 n-1 ，以及一个边数组 edges ，其中 edges[i] = [fromi, toi] 表示一条从点  fromi 到点 toi 的有向边。
     * 找到最小的点集使得从这些点出发能到达图中所有点。题目保证解存在且唯一。
     * 你可以以任意顺序返回这些节点编号。
     * 示例 1：
     * 输入：n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
     * 输出：[0,3]
     * 解释：从单个节点出发无法到达所有节点。从 0 出发我们可以到达 [0,1,2,5] 。从 3 出发我们可以到达 [3,4,2,5] 。所以我们输出 [0,3] 。
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> degree = new HashSet<>();
        for(List<Integer> edge : edges) {
            degree.add(edge.get(1));
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!degree.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }
    /**
     * 方法一：寻找入度为零的节点
     * 对于任意节点 xx，如果其入度不为零，则一定存在节点 yy 指向节点 xx，从节点 yy 出发即可到达节点 yy 和节点 xx，因此如果从节点 yy 出发，节点 xx 和节点 yy 都可以到达，且从节点 yy 出发可以到达的节点比从节点 xx 出发可以到达的节点更多。
     * 由于给定的图是有向无环图，基于上述分析可知，对于任意入度不为零的节点 xx，一定存在另一个节点 zz，使得从节点 zz 出发可以到达节点 xx。为了获得最小的点集，只有入度为零的节点才应该加入最小的点集。
     * 由于入度为零的节点必须从其自身出发才能到达该节点，从别的节点出发都无法到达该节点，因此最小的点集必须包含所有入度为零的节点。
     * 因为入度不为零的点总可以由某个入度为零的点到达，所以这些点不包括在最小的合法点集当中。
     * 因此，我们得到「最小的点集使得从这些点出发能到达图中所有点」就是入度为零的所有点的集合。
     * 如何判断一个节点的入度是否为零呢？在有向图中，一个节点的入度等于以该节点为终点的有向边的数量，因此一个节点的入度为零，当且仅当对于任何有向边，该节点都不是有向边的终点。
     * 因此，可以遍历所有的边，使用集合存储所有有向边的终点，集合中的所有节点即为入度不为零的节点，剩下的所有节点即为入度为零的节点。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-vertices-to-reach-all-nodes/solution/ke-yi-dao-da-suo-you-dian-de-zui-shao-dian-shu-m-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     * 解题思路
     * 此题是拓扑排序的入门级题目。没有要求给出拓扑排序顺序。只是问最早多少个点开始就能访问到图中所有节点。首先这些
     * 点必须是起点。即有向图概念中入度为0的点。拓扑排序的时候可以从这些起点的任何一个开始，因为他们没有前驱（Predecessor），访问没有限制。
     * 正常的拓扑排序是访问完一个节点，把跟它相邻节点的入度减一，如果邻居节点的入度也变为0，说明它的前驱也都访问过了，可以继续访问。
     * 这样一直进行下去直到所有节点都访问过了或者发现有向图中有环无法给出拓扑排序序列为止。通常是用队列模拟我们上面提及的排序步骤。
     * 但是由于该题目只是要求起始点的集合，所以只要用一个入度数组inDgree[n]统计每个节点的入度，返回那些一开始入度即为0的节点集合即可。
     * 作者：jyj407
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-vertices-to-reach-all-nodes/solution/zhong-gui-zhong-ju-tuo-bu-pai-xu-by-jyj407/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
