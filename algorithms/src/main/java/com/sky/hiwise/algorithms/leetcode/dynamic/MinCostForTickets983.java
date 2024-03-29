package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinCostForTickets983 {
    /**
     * 983. 最低票价
     * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
     * 火车票有三种不同的销售方式：
     * 一张为期一天的通行证售价为 costs[0] 美元；
     * 一张为期七天的通行证售价为 costs[1] 美元；
     * 一张为期三十天的通行证售价为 costs[2] 美元。
     * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
     * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
     * 示例 1：
     * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
     * 输出：11
     * 解释：
     * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
     * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
     * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
     * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
     * 你总共花了 $11，并完成了你计划的每一天旅行。
     */
    int[] costs;
    int[] memo;
    Set<Integer> daySets = new HashSet<>();
    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new int[366];
        Arrays.fill(memo, -1);
        for (int day : days) {
            daySets.add(day);
        }
        return dp(1);
    }

    public int dp(int i) {
        if (i > 365) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        if (daySets.contains(i)) {
            memo[i] = Math.min(dp(i + 1) + costs[0], dp(i + 7) + costs[1]);
            memo[i] = Math.min(memo[i], dp(i + 30) + costs[2]);
        } else {
            memo[i] = dp(i + 1);
        }
        return memo[i];
    }
    /**
     * 方法一：记忆化搜索（日期变量型）
     * 思路和算法
     * 我们用 \textit{dp}(i)dp(i) 来表示从第 ii 天开始到一年的结束，我们需要花的钱。考虑到一张通行证可以让我们在「接下来」的若干天进行旅行，所以我们「从后往前」倒着进行动态规划。
     * 对于一年中的任意一天：
     * 如果这一天不是必须出行的日期，那我们可以贪心地选择不买。这是因为如果今天不用出行，那么也不必购买通行证，并且通行证越晚买越好。所以有 \textit{dp}(i) = \textit{dp}(i + 1)dp(i)=dp(i+1)；
     * 如果这一天是必须出行的日期，我们可以选择买 11，77 或 3030 天的通行证。若我们购买了 jj 天的通行证，那么接下来的 j - 1j−1 天，我们都不再需要购买通行证，只需要考虑第 i + ji+j 天及以后即可。因此，我们有
     * \textit{dp}(i) = \min\{\textit{cost}(j) + \textit{dp}(i + j)\}, \quad j \in \{1, 7, 30\}
     * dp(i)=min{cost(j)+dp(i+j)},j∈{1,7,30}
     * 其中 \textit{cost}(j)cost(j) 表示 jj 天通行证的价格。为什么我们只需要考虑第 i+ji+j 天及以后呢？这里和第一条的贪心思路是一样的，如果我们需要购买通行证，那么一定越晚买越好，在握着一张有效的通行证的时候购买其它的通行证显然是不划算的。
     * 由于我们是倒着进行动态规划的，因此我们可以使用记忆化搜索，减少代码的编写难度。我们使用一个长度为 366366 的数组（因为天数是 [1, 365][1,365]，而数组的下标是从 00 开始的）存储所有的动态规划结果，这样所有的 \textit{dp}(i)dp(i) 只会被计算一次（和普通的动态规划相同），时间复杂度不会增大。
     * 最终的答案记为 \textit{dp}(1)dp(1)。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets/solution/zui-di-piao-jie-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    public int mincostTickets1(int[] days, int[] costs) {
        int len = days.length, maxDay = days[len - 1], minDay = days[0];
        int[] dp = new int[maxDay + 31];
        for (int d = maxDay, i = len  - 1; d >= minDay; d--) {
            if (d == days[i]) {
                dp[d] = Math.min(dp[d + 1] + costs[0], dp[d + 7] + costs[1]);
                dp[d] = Math.min(dp[d], dp[d + 30] + costs[2]);
                i--;
            } else {
                dp[d] = dp[d + 1];
            }
        }
        return dp[minDay];
    }
}
