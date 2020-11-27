package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;

public class MaxGap164 {
    /**
     * 164. 最大间距
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     * 如果数组元素个数小于 2，则返回 0。
     * 示例 1:
     * 输入: [3,6,9,1]
     * 输出: 3
     * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
     * 示例 2:
     * 输入: [10]
     * 输出: 0
     * 解释: 数组元素个数小于 2，因此返回 0。
     * 说明:
     * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
     * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
     */
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int minVal = Arrays.stream(nums).min().getAsInt();
        int d = Math.max(1, (maxVal - minVal) / (len - 1));
        int bucketSize = (maxVal - minVal) / d + 1;
        int[][] buckets = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; i++) {
            Arrays.fill(buckets[i], -1);
        }
        for (int i = 0; i < len; i++) {
            int idx = (nums[i] - minVal) / d;
            if (buckets[idx][0] == -1){
                buckets[idx][0] = buckets[idx][1] = nums[i];
            } else {
                buckets[idx][0] = Math.min(buckets[idx][0], nums[i]);
                buckets[idx][1] = Math.max(buckets[idx][1], nums[i]);
            }
        }


        int ans = 0, pre = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (buckets[i][0] == -1) {
                continue;
            }
            if (pre != -1) {
                ans = Math.max(ans, buckets[i][0] - buckets[pre][1]);
            }
            pre = i;
        }
        return ans;
    }

    /**
     * 方法二：基于桶的算法
     * 思路与算法
     * 设长度为 NN 的数组中最大值为 \textit{max,min}max,min，则不难发现相邻数字的最大间距不会小于 \lceil (\textit{max}-\textit{min}) / (N-1) \rceil⌈(max−min)/(N−1)⌉。
     * 因此，我们可以选取整数 d = \lfloor (\textit{max}-\textit{min}) / (N-1) \rfloor < \lceil (\textit{max}-\textit{min}) / (N-1) \rceild=⌊(max−min)/(N−1)⌋<⌈(max−min)/(N−1)⌉。
     * 随后，我们将整个区间划分为若干个大小为 dd 的桶，并找出每个整数所在的桶。根据前面的结论，能够知道，元素之间的最大间距一定不会出现在某个桶的内部，而一定会出现在不同桶当中。
     * 因此，在找出每个元素所在的桶之后，我们可以维护每个桶内元素的最大值与最小值。随后，只需从前到后不断比较相邻的桶，用后一个桶的最小值与前一个桶的最大值之差作为两个桶的间距，最终就能得到所求的答案。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-gap/solution/zui-da-jian-ju-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
