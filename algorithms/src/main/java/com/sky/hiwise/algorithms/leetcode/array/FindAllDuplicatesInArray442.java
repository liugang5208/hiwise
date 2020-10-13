package com.sky.hiwise.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllDuplicatesInArray442 {

    /**
     * 442. 数组中重复的数据
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     * 找到所有出现两次的元素。
     * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     * 示例：
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * 输出:
     * [2,3]
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int realNum = Math.abs(nums[i]);
            int index = realNum - 1;
            nums[index] = -nums[index];
            if (nums[index] > 0) {
                ans.add(realNum);
            }
        }
        return  ans;
    }
    /**
     * input [4,3,2,7,8,2,3,1] 对于第一个数字4 的出现次数，用index 3 的数字7的正负值表示
     * 4出现第一次，7变-7，出现第二次-7变7并加入result
     *
     * 作者：jia-mei-wang-ye
     * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/solution/java-on-dai-jie-shi-by-jia-mei-wang-ye/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * 示例 1:
     * 输入: [1,3,4,2,2]
     * 输出: 2
     * 示例 2:
     * 输入: [3,1,3,4,2]
     * 输出: 3
     * 说明：
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        return -1;
    }

    /**
     * 1299. 将每个元素替换为右侧最大元素
     * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
     * 完成所有替换操作后，请你返回这个数组。
     * 示例：
     * 输入：arr = [17,18,5,4,6,1]
     * 输出：[18,6,6,6,1,-1]
     * 提示：
     * 1 <= arr.length <= 10^4
     * 1 <= arr[i] <= 10^5
     */
    public int[] replaceElements(int[] arr) {
        int[] ans = new int[arr.length];
        ans[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            ans[i] = Math.max(ans[i+1], arr[i+1]);
        }
        return ans;
    }

    /**
     * 961. 重复 N 次的元素
     * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
     * 返回重复了 N 次的那个元素。
     * 示例 1：
     * 输入：[1,2,3,3]
     * 输出：3
     * 示例 2：
     * 输入：[2,1,2,5,3,2]
     * 输出：2
     * 示例 3：
     * 输入：[5,1,5,2,5,3,5,4]
     * 输出：5
     * @param A
     * @return
     */
    public int repeatedNTimes(int[] A) {
        for (int k = 1; k <= 3; ++k) {
            for (int i = 0; i < A.length - k; ++i){
                if (A[i] == A[i+k]) {
                    return A[i];
                }
            }
        }
        return -1;
    }
    /**
     * 方法 2：比较
     * 想法和算法
     * 一旦找到一个重复元素，那么一定就是答案。我们称这个答案为主要元素。
     * 考虑所有长度为 4 的子序列，在子序列中一定至少含有两个主要元素。
     * 这是因为：
     * 长度为 2 的子序列中都是主要元素，或者；
     * 每个长度为 2 的子序列都恰好含有 1 个主要元素，这意味着长度为 4 的子序列一定含有 2 个主要元素。
     * 因此，只需要比较所有距离为 1，2 或者 3 的邻居元素即可。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array/solution/zhong-fu-n-ci-de-yuan-su-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
