package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.TreeSet;

public class ThirdMaxNumber414 {
    /**
     * 414. 第三大的数
     * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
     * 示例 1：
     * 输入：[3, 2, 1]
     * 输出：1
     * 解释：第三大的数是 1 。
     * 示例 2：
     * 输入：[1, 2]
     * 输出：2
     * 解释：第三大的数不存在, 所以返回最大的数 2 。
     * 示例 3：
     * 输入：[2, 2, 3, 1]
     * 输出：1
     * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
     * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
     */
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        return set.size() == 3 ? set.first() : set.last();
    }
    /**
     * 方法二：有序集合
     * 我们可以遍历数组，同时用一个有序集合来维护数组中前三大的数。
     * 具体做法是每遍历一个数，就将其插入有序集合，若有序集合的大小超过 3，就删除集合中的最小元素。
     * 这样可以保证有序集合的大小至多为 3，且遍历结束后，若有序集合的大小为 3，其最小值就是数组中第三大的数；
     * 若有序集合的大小不足 3，那么就返回有序集合中的最大值。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/third-maximum-number/solution/di-san-da-de-shu-by-leetcode-solution-h3sp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
