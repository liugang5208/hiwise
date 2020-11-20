package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

public class MinDaysEatOranges1553 {
    /**
     * 1553. 吃掉 N 个橘子的最少天数
     * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
     *
     * 吃掉一个橘子。
     * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
     * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
     * 每天你只能从以上 3 种方案中选择一种方案。
     *
     * 请你返回吃掉所有 n 个橘子的最少天数。
     * 示例 1：
     * 输入：n = 10
     * 输出：4
     * 解释：你总共有 10 个橘子。
     * 第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
     * 第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
     * 第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
     * 第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
     * 你需要至少 4 天吃掉 10 个橘子。
     */
    Map<Integer, Integer> memo = new HashMap<>();
    public int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int fi = Math.min(n % 2 + 1 + minDays( n / 2), n % 3 + 1 + minDays(n / 3));
        memo.put(n, fi);
        return fi;
    }
    /**
     * 根据上面的分析，我们可以得到三条重要的结论：
     *
     * 在任意一次操作 22 之前最多只会有 11 次操作 11；
     *
     * 对于任意的橘子数 i \geq 2i≥2，唯一的操作方法是将 nn 通过操作 11 减少到最近的 22 的倍数，随后进行一次操作 22。写成递推式即为：
     * f(i) = i \% 2 + 1 + f(\lfloor i/2 \rfloor)
     * f(i)=i%2+1+f(⌊i/2⌋)
     *
     * 在任意一次操作 33 之前最多只会有 22 次操作 11；
     *
     * 对于任意的橘子数 i \geq 3i≥3，唯一的操作方法是将 nn 通过操作 11 减少到最近的 33 的倍数，随后进行一次操作 33。写成递推式即为：
     * f(i) = i \% 3 + 1 + f(\lfloor i/3 \rfloor)
     * f(i)=i%3+1+f(⌊i/3⌋)
     *
     * 除了最后的一次操作 11 之外，其余连续的操作 11 之后都会有操作 22 或 33。即：
     *
     * f(1) = 1
     * f(1)=1
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-eat-n-oranges/solution/chi-diao-n-ge-ju-zi-de-zui-shao-tian-shu-by-leetco/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
