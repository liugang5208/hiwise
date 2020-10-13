package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class HouseRobber198 {

    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     * 示例 1:
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2:
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * @param nums
     * @return
     */
    public static void main(String[] args) {
        int[] nums = {10,4,4232,4,666,23,23,34,677,2,2,23737,442,63,25,2,9999};
        int res = (new HouseRobber198()).rob(nums);
        System.out.println("-------------");
        System.out.println(res);
    }
    int memo[];
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        memo = new int[n];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0);
    }

    public int tryRob(int[] nums, int index) {
       if (index >= nums.length) {
           return 0;
       }
       if (memo[index] != -1) {
           return memo[index];
       }
       int res = 0;

       for(int i = index; i < nums.length; i++) {
           res = Math.max(res, nums[i] + tryRob(nums, i + 2));
       }
        memo[index] = res;
       return res;
    }

    //memo[i] 表示考虑从[i....n-1]能抢劫的最大收益
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        memo = new int[n];
        Arrays.fill(memo, -1);
        memo[n - 1] = nums[n - 1];
        for(int i = n - 2; i > 0; i--) {
            for(int j = i; j < n; j++) {
               memo[i] = Math.max(memo[i], nums[j] + (j + 2 < n ? memo[j + 2] : 0));
            }
        }
        return memo[0];
    }

    /**
     198. 解题思路：
     典型的动态规划，以下按照标准流程解题。
     状态定义：
     设动态规划列表 dp ，dp[i] 代表前 i 个房子在满足条件下的能偷窃到的最高金额。
     转移方程：
     设： 有 n 个房子，前 n 间能偷窃到的最高金额是 dp[n] ，前 n−1 间能偷窃到的最高金额是 dp[n-1] ，此时向这些房子后加一间房，此房间价值为 num ；
     加一间房间后： 由于不能抢相邻的房子，意味着抢第 n+1 间就不能抢第 n 间；那么前 n+1 间房能偷取到的最高金额 dp[n+1] 一定是以下两种情况的 较大值 ：
     不抢第 n+1 个房间，因此等于前 n 个房子的最高金额，即 dp[n+1] = dp[n]；
     抢第 n+1 个房间，此时不能抢第 n 个房间；因此等于前 n−1 个房子的最高金额加上当前房间价值，即 dp[n+1] = dp[n-1] + num；
     细心的我们发现： 难道在前 n 间的最高金额 dp[n] 情况下，第 n 间一定被偷了吗？假设没有被偷，那 n+1 间的最大值应该也可能是 dp[n+1] = dp[n] + num 吧？其实这种假设的情况可以被省略，这是因为：
     假设第 n 间没有被偷，那么此时 dp[n] = dp[n-1] ，此时 dp[n+1] = dp[n] + num = dp[n-1] + num，即可以将 两种情况合并为一种情况 考虑；
     假设第 n 间被偷，那么此时 dp[n+1] = dp[n] + num不可取 ，因为偷了第 n 间就不能偷第n+1 间。
     最终的转移方程： dp[n+1] = max(dp[n],dp[n-1]+num)
     初始状态：
     前 00 间房子的最大偷窃价值为 0 ，即 dp[0] = 0
     返回值：
     返回 dp 列表最后一个元素值，即所有房间的最大偷窃价值。
     简化空间复杂度：
     我们发现 dp[n] 只与 dp[n−1] 和 dp[n−2] 有关系，因此我们可以设两个变量 cur和 pre 交替记录，将空间复杂度降到 O(1) 。
     复杂度分析：
     时间复杂度 O(N) ： 两次遍历 nums 需要线性时间；
     空间复杂度 O(1) ： cur和 pre 使用常数大小的额外空间。
     */
    public int rob3(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return dp[nums.length];
    }

    public int rob4(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int pre = 0, cur = nums[0], tmp;
        for (int i = 1; i < n; i++) {
            tmp = cur;
            cur = Math.max(cur, pre + nums[i]);
            pre = tmp;
        }
       return cur;
    }
}
