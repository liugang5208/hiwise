package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.ArrayList;
import java.util.List;

public class FindNumDisappearInArray448 {
    /**
     * 448. 找到所有数组中消失的数字
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     * 示例:
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * 输出:
     * [5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] = nums[x] + n;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
    /**
     * 方法一：原地修改
     * 思路及解法
     * 我们可以用一个哈希表记录数组 \textit{nums}nums 中的数字，由于数字范围均在 [1,n][1,n] 中，记录数字后我们再利用哈希表检查 [1,n][1,n] 中的每一个数是否出现，从而找到缺失的数字。
     * 由于数字范围均在 [1,n][1,n] 中，我们也可以用一个长度为 nn 的数组来代替哈希表。这一做法的空间复杂度是 O(n)O(n) 的。我们的目标是优化空间复杂度到 O(1)O(1)。
     * 注意到 \textit{nums}nums 的长度恰好也为 nn，能否让 \textit{nums}nums 充当哈希表呢？
     * 由于 \textit{nums}nums 的数字范围均在 [1,n][1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
     * 具体来说，遍历 \textit{nums}nums，每遇到一个数 xx，就让 \textit{nums}[x-1]nums[x−1] 增加 nn。由于 \textit{nums}nums 中所有数均在 [1,n][1,n] 中，增加以后，这些数必然大于 nn。最后我们遍历 \textit{nums}nums，若 \textit{nums}[i]nums[i] 未大于 nn，就说明没有遇到过数 i+1i+1。这样我们就找到了缺失的数字。
     * 注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 nn 取模来还原出它本来的值。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/zhao-dao-suo-you-shu-zu-zhong-xiao-shi-d-mabl/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
