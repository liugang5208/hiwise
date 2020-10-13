package com.sky.hiwise.algorithms.leetcode.array;

public class MaxChunksMakeSorted769 {

    /**
     * 769. 最多能完成排序的块
     * 数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。
     * 之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
     * 我们最多能将数组分成多少块？
     * 示例 1:
     * 输入: arr = [4,3,2,1,0]
     * 输出: 1
     * 解释:
     * 将数组分成2块或者更多块，都无法得到所需的结果。
     * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
     * 示例 2:
     * 输入: arr = [1,0,2,3,4]
     * 输出: 4
     * 解释:
     * 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
     * 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ans ++;
            }
        }
        return ans;
    }
    /**
     * 思路和算法
     *
     * 首先找到从左块开始最小块的大小。如果前 k 个元素为 [0, 1, ..., k-1]，可以直接把他们分为一个块。
     * 当我们需要检查 [0, 1, ..., n-1] 中前 k+1 个元素是不是 [0, 1, ..., k] 的时候，只需要检查其中最大的数是不是 k 就可以了。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/max-chunks-to-make-sorted/solution/zui-duo-neng-wan-cheng-pai-xu-de-kuai-i-by-leetcod/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
