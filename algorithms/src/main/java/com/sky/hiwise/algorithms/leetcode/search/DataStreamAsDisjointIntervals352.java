package com.sky.hiwise.algorithms.leetcode.search;

import java.util.ArrayList;
import java.util.List;

public class DataStreamAsDisjointIntervals352 {
    /**
     * 352. 将数据流变为多个不相交区间
     *  给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
     * 实现 SummaryRanges 类：
     * SummaryRanges() 使用一个空数据流初始化对象。
     * void addNum(int val) 向数据流中加入整数 val 。
     * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
     * 示例：
     * 输入：
     * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
     * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
     * 输出：
     * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
     * 解释：
     * SummaryRanges summaryRanges = new SummaryRanges();
     * summaryRanges.addNum(1);      // arr = [1]
     * summaryRanges.getIntervals(); // 返回 [[1, 1]]
     * summaryRanges.addNum(3);      // arr = [1, 3]
     * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
     * summaryRanges.addNum(7);      // arr = [1, 3, 7]
     * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
     * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
     * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
     * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
     * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
     */
    class SummaryRanges {

        private List<int[]> list;
        public SummaryRanges() {
            list = new ArrayList<>();
        }

        public void addNum(int val) {
            int n = list.size();
            if (n == 0) {
                list.add(new int[]{val, val});
                return;
            }
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - (l - 1)) / 2; // l + r + 1 >> 1
                if (list.get(mid)[0] <= val) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int[] curr = list.get(r);
            if (curr[0] > val) {
                if (val + 1 == curr[0]) {
                    curr[0] = val;
                } else {
                    list.add(r, new int[]{val, val});
                }
                return;
            }
            if (val >= curr[0] && val <= curr[1]) {
                //pass
            } else if (r == n - 1) {
                if (curr[1] + 1 == val) {
                    curr[1] = val;
                } else {
                    list.add(new int[]{val, val});
                }
            } else {
                int[] next = list.get(r + 1);
                if (curr[1] + 1 == val && next[0] - 1 == val) {
                    curr[1] = next[1];
                    list.remove(r + 1);
                } else if (curr[1] + 1 == val) {
                    curr[1] = val;
                } else if (next[0] - 1 == val) {
                    next[0] = val;
                } else {
                    list.add(r + 1, new int[]{val, val});
                }
            }
        }

        public int[][] getIntervals() {
            int n = list.size();
            int[][] ans = new int[n][2];
            for (int i = 0; i < n; i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }
        /**
         * 如果我们的数据结构能够快速找到这两个区间，那么上面的五种情况分别对应为：
         *
         * 情况一：l0≤val≤l1   在区间里不需要处理
         * 情况二：r0 + 1 = val
         * 情况三：l1 - 1 = val
         * 情况四：r0 + 1 = val 且 l1 - 1 = val  需要合并成大区间
         * 情况五：上述情况均不满足。
         * 作者：LeetCode-Solution
         * 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/solution/jiang-shu-ju-liu-bian-wei-duo-ge-bu-xian-hm1r/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
    }
}
