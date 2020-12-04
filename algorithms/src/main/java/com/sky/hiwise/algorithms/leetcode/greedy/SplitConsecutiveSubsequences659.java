package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

public class SplitConsecutiveSubsequences659 {
    /**
     * 659. 分割数组为连续子序列
     * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
     * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入: [1,2,3,3,4,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3
     * 3, 4, 5
     * 示例 2：
     * 输入: [1,2,3,3,4,4,5,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3, 4, 5
     * 3, 4, 5
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> nc = new HashMap<>();
        for (int num : nums) {
            nc.put(num, nc.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> tail = new HashMap<>();
        for (int num : nums) {
            int count = nc.get(num);
            if (count <= 0) {
                continue;
            } else if (nc.get(num) > 0 && tail.getOrDefault(num - 1, 0) > 0) {
                nc.put(num, nc.get(num) - 1);
                tail.put(num - 1, tail.get(num - 1) - 1);
                tail.put(num, tail.getOrDefault(num, 0) + 1);
            } else if (nc.get(num) > 0 && nc.getOrDefault(num + 1, 0) > 0 && nc.getOrDefault(num + 2, 0) > 0) {
                nc.put(num, nc.get(num) - 1);
                nc.put(num + 1, nc.get(num + 1) - 1);
                nc.put(num + 2, nc.get(num + 2) - 1);
                tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
    /**
     * 算法思路
     * 首先使用两个哈希 mapnc和tail
     *
     * nc[i]：存储原数组中数字i出现的次数
     * tail[i]：存储以数字i结尾的且符合题意的连续子序列个数
     * 先去寻找一个长度为3的连续子序列 i, i+1, i+2，找到后就将 nc[i], nc[i+1], nc[i+2] 中对应数字消耗1个（即-1），
     * 并将 tail[i+2] 加 1，即以 i+2 结尾的子序列个数 +1。
     * 如果后续发现有能够接在这个连续子序列的数字 i+3，则延长以 i+2 为结尾的连续子序列到 i+3，
     * 此时消耗 nc[i+3] 一个，由于子序列已延长，因此tail[i+2] 减 1，tail[i+3] 加 1
     * 在不满足上面的情况下
     * 如果 nc[i] 为 0，说明这个数字已经消耗完，可以不管了
     * 如果 nc[i] 不为 0，说明这个数字多出来了，且无法组成连续子序列，所以可以直接返回 false 了
     * 因此，只有检查到某个数时，这个数未被消耗完，且既不能和前面组成连续子序列，也不能和后面组成连续子序列时，无法分割
     *
     * 作者：aspenstarss
     * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/solution/tan-xin-suan-fa-jian-cha-shu-zu-neng-fou-bei-fen-w/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
