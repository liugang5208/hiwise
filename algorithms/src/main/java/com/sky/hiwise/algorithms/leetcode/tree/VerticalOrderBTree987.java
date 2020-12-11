package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VerticalOrderBTree987 {
    /**
     * 987. 二叉树的垂序遍历
     * 给定二叉树，按垂序遍历返回其结点值。
     * 对位于 (X, Y) 的每个结点而言，其左右子结点分别位于 (X-1, Y-1) 和 (X+1, Y-1)。
     * 把一条垂线从 X = -infinity 移动到 X = +infinity ，每当该垂线与结点接触时，我们按从上到下的顺序报告结点的值（ Y 坐标递减）。
     * 如果两个结点位置相同，则首先报告的结点值较小。
     * 按 X 坐标顺序返回非空报告的列表。每个报告都有一个结点值列表。
     * 示例 1：
     * 输入：[3,9,20,null,null,15,7]
     * 输出：[[9],[3,15],[20],[7]]
     * 解释：
     * 在不丧失其普遍性的情况下，我们可以假设根结点位于 (0, 0)：
     * 然后，值为 9 的结点出现在 (-1, -1)；
     * 值为 3 和 15 的两个结点分别出现在 (0, 0) 和 (0, -2)；
     * 值为 20 的结点出现在 (1, -1)；
     * 值为 7 的结点出现在 (2, -2)。
     * 示例 2：
     */
    List<Location> locations;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        locations = new ArrayList<>();
        dfs(root, 0, 0);
        Collections.sort(locations);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        int prev = locations.get(0).x;

        for (Location location : locations) {
            if (location.x != prev) {
                prev = location.x;
                ans.add(new ArrayList<>());
            }
            ans.get(ans.size() - 1).add(location.val);
        }
        return ans;
    }

    public void dfs(TreeNode node, int x, int y) {
        if (node != null) {
            locations.add(new Location(x, y, node.val));
            dfs(node.left, x - 1, y + 1);
            dfs(node.right, x + 1, y + 1);
        }
    }

    class Location implements Comparable<Location> {
        public int x;
        public int y;
        public int val;
        public Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Location o) {
            if (this.x != o.x) {
                return Integer.compare(this.x, o.x);
            } else if (this.y != o.y) {
                return Integer.compare(this.y, o.y);
            } else {
                return Integer.compare(this.val, o.val);
            }
        }
    }
}
