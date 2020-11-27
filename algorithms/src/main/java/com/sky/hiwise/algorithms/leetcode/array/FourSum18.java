package com.sky.hiwise.algorithms.leetcode.array;

import java.util.*;

public class FourSum18 {
    /**
     * 18. 四数之和
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * 注意：
     *
     * 答案中不可以包含重复的四元组。
     *
     * 示例：
     *
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int size = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (size < 4) {
            return ans;
        }
        Arrays.sort(nums);
        for (int a = 0; a <= size - 4; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int b = a + 1; b <= size - 3; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int c = b + 1, d = size - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < target) {
                        c++;
                    } else if (sum > target) {
                        d--;
                    } else {
                        List<Integer> temp = Arrays.asList(nums[a], nums[b], nums[c], nums[d]);
                        ans.add(temp);
                        while(c < d && nums[c + 1] == nums[c]) {
                            c++;
                        }
                        while(c < d && nums[d - 1] == nums[d]) {
                            d--;
                        }
                        c++;
                        d--;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 454. 四数相加 II
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
     * 例如:
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     * 输出:
     * 2
     * 解释:
     * 两个元组如下:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                countAB.put(a + b, countAB.getOrDefault(a + b, 0) + 1);
            }
        }
        int ans = 0;
        for (int c : C) {
            for (int d : D) {
                int key = - (c + d);
                if (countAB.containsKey(key)) {
                    ans += countAB.get(key);
                }
            }
        }
        return ans;
    }
    /**
     * 方法一：分组 + 哈希表
     * 思路与算法
     * 我们可以将四个数组分成两部分，AA 和 BB 为一组，CC 和 DD 为另外一组。
     * 对于 AA 和 BB，我们使用二重循环对它们进行遍历，得到所有 A[i]+B[j]A[i]+B[j] 的值并存入哈希映射中。对于哈希映射中的每个键值对，
     * 每个键表示一种 A[i]+B[j]A[i]+B[j]，对应的值为 A[i]+B[j]A[i]+B[j] 出现的次数。
     * 对于 CC 和 DD，我们同样使用二重循环对它们进行遍历。当遍历到 C[k]+D[l]C[k]+D[l] 时，如果 -(C[k]+D[l])−(C[k]+D[l]) 出现在哈希映射中，
     * 那么将 -(C[k]+D[l])−(C[k]+D[l]) 对应的值累加进答案中。
     * 最终即可得到满足 A[i]+B[j]+C[k]+D[l]=0A[i]+B[j]+C[k]+D[l]=0 的四元组数目。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/4sum-ii/solution/si-shu-xiang-jia-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
