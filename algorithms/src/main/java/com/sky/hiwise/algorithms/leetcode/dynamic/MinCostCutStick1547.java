package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class MinCostCutStick1547 {
    /**
     * 1547. 切棍子的最小成本
     * 有一根长度为 n 个单位的木棍，棍上从 0 到 n 标记了若干位置。例如，长度为 6 的棍子可以标记如下：
     * 给你一个整数数组 cuts ，其中 cuts[i] 表示你需要将棍子切开的位置。
     * 你可以按顺序完成切割，也可以根据需要更改切割的顺序。
     * 每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和。
     * 对棍子进行切割将会把一根木棍分成两根较小的木棍（这两根木棍的长度和就是切割前木棍的长度）。请参阅第一个示例以获得更直观的解释。
     * 返回切棍子的 最小总成本 。
     * 示例 1：
     * 输入：n = 7, cuts = [1,3,4,5]
     * 输出：16
     * 解释：按 [1, 3, 4, 5] 的顺序切割的情况如下所示：
     * 第一次切割长度为 7 的棍子，成本为 7 。第二次切割长度为 6 的棍子（即第一次切割得到的第二根棍子），第三次切割为长度 4 的棍子，最后切割长度为 3 的棍子。总成本为 7 + 6 + 4 + 3 = 20 。
     * 而将切割顺序重新排列为 [3, 5, 1, 4] 后，总成本 = 16（如示例图中 7 + 4 + 3 + 2 = 16）。
     */
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        int[] newCuts = new int[m + 2];
        newCuts[0] = 0;
        for (int i = 1; i <= m; i++) {
            newCuts[i] = cuts[i - 1];
        }
        newCuts[m + 1] = n;
        int[][] dp = new int[m + 2][m + 2];
        for (int i = m; i >= 1; i--) {
            for (int j = i; j <= m; j++) {
                dp[i][j] = i == j ? 0 : Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
                }
                dp[i][j] += newCuts[j + 1] - newCuts[i - 1];
            }
        }
        return dp[1][m];
    }
    /**
     * 方法一：动态规划
     * 思路与算法
     * 在我们任意一次切割时，待切割木棍的左端点要么是原始木棍的左端点 00，要么是之前某一次切割的位置；
     * 同理，待切割木棍的右端点要么是原始木棍的右端点 nn，要么是之前某一次切割的位置。
     * 因此，如果我们将切割位置数组 \textit{cuts}cuts 进行排序，并在左侧添加 00，右侧添加 nn，那么待切割的木棍就对应着数组中一段连续的区间。这样一来，我们就可以用动态规划来解决本题。
     * 我们用数组 \textit{cuts}[1..m]cuts[1..m] 表示题目中给定的数组 \textit{cuts}cuts 按照升序排序后的结果，其中 mm 是数组 \textit{cuts}cuts 的长度，并令 cuts[0] = 0cuts[0]=0，cuts[m+1] = ncuts[m+1]=n。同时，我们用 f[i][j]f[i][j] 表示在当前待切割的木棍的左端点为 \textit{cuts}[i-1]cuts[i−1]，右端点为 \textit{cuts}[j+1]cuts[j+1] 时，将木棍全部切开的最小总成本。
     * 这里全部切开的意思是，木棍中有 j-i+1j−i+1 个切割位置 \textit{cuts}[i], \cdots, \textit{cuts}[j]cuts[i],⋯,cuts[j]，我们需要将木棍根据这些位置，切割成 j-i+2j−i+2 段。
     * 为了得到最小总成本，我们可以枚举第一刀的位置。如果第一刀的位置为 \textit{cuts}[k]cuts[k]，其中 k \in [i, j]k∈[i,j]，那么我们会将待切割的木棍分成两部分，
     * 左侧部分的木棍为 \textit{cuts}[i-1..k]cuts[i−1..k]，对应的可以继续切割的位置为 \textit{cuts}[i..k-1]cuts[i..k−1]；右侧部分的木棍为 \textit{cuts}[k..j+1]cuts[k..j+1]，对应的可以继续切割的位置为 \textit{cuts}[k+1..j]cuts[k+1..j]。由于左右两侧均为规模较小的子问题，因此我们可以得到状态转移方程：
     * f[i][j] = \min_{k \in [i,j]} \{ f[i][k-1] + f[k+1][j] \} + (\textit{cuts}[j+1] - \textit{cuts}[i-1])
     * 即我们无论在哪里切第一刀，这一刀的成本都是木棍的长度 \textit{cuts}[j+1] - \textit{cuts}[i-1]cuts[j+1]−cuts[i−1]。
     * 状态转移方程的边界条件为：
     * f[i][j] = 0, ~其中~ i > j
     * 也就是说，如果没有可以切割的位置，那么它要么是一根无法再切割的木棍（此时 i=j+1i=j+1），要么根本就不是一根木棍（此时 i>j+1i>j+1）。无论是哪一种情况，对应的最小总成本都是 00。
     * 最后的答案即为 f[1][m]f[1][m]。
     * 细节
     * 在区间动态规划中，我们要注意状态计算的顺序，即在计算 f[i][j]f[i][j] 时，所有满足 k \in [i, j]k∈[i,j] 的 f[i][k]f[i][k] 和 f[k][j]f[k][j] 都需要已经被计算过。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-cost-to-cut-a-stick/solution/qie-gun-zi-de-zui-xiao-cheng-ben-by-leetcode-solut/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
