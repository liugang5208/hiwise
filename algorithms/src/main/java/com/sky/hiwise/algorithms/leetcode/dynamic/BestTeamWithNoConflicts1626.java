package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class BestTeamWithNoConflicts1626 {
    /**
     * 1626. 无矛盾的最佳球队
     * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
     * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
     * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
     * 示例 1：
     * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
     * 输出：34
     * 解释：你可以选中所有球员。
     * 示例 2：
     * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
     * 输出：16
     * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
     * 示例 3：
     * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
     * 输出：6
     * 解释：最佳的选择是前 3 名球员。
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        if (scores.length == 1) {
            return scores[0];
        }
        int[][] sa = new int[scores.length][2];
        for (int i = 0; i < scores.length; i++) {
            sa[i][0] = ages[i];
            sa[i][1] = scores[i];
        }
        Arrays.sort(sa, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int[] dp = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            dp[i] = sa[i][1];
        }

        int ans = 0;
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < i; j++) {  //找在i之前有没有比i小的
                if (sa[j][1] <= sa[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + sa[i][1]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    /**
     * 按年龄递增排序，年龄相同的按分数递增排序
     * 比如scores = [4,5,6,5], ages = [2,1,2,1]
     * 排序后为：ages: 1,1,2,2
     * scores: 5,5,4,6
     * 这样排序的目的：
     * 要首先保证年龄是符合规则的,就可以不看年龄，只考虑分数了。这时只需挑选分数不降的组成序列，保证和最大就可以了。（转化为LIS问题）
     * 相同的题目看LeetCode300.最长上升子序列
     * 作者：likeriver
     * 链接：https://leetcode-cn.com/problems/best-team-with-no-conflicts/solution/ben-zhi-shi-liswen-ti-pai-xu-dp-by-likeriver/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 经典贪心dp问题
     * 维护一个二维数组mp，存储每个球员年龄以及和所得的分数。
     * 将数组排序，以便后续贪心，排序后年龄小的优先在前，年龄相同则分值小的在前。（排序是为了快速找到转移过来的选择）
     * 维护一个一维数组dp记录从0到i中，dp[i]表示选取下标i结尾的球员所能拿到的最大分数。
     * 开始贪心，对于下标i，从下标i-1开始往前寻找前一个适应球员，并更新最大值，遍历完最后再加上下标i球员的得分。（由于已经排序，就不需要再比较年龄）
     * 每次确定dp[i]的值后，如果dp[i] > maxs， 则更新maxs，最后返回最大值maxs
     * 作者：spacex-1
     * 链接：https://leetcode-cn.com/problems/best-team-with-no-conflicts/solution/jing-dian-tan-xin-dong-tai-gui-hua-you-tu-you-zhu-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
