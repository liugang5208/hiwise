package com.sky.hiwise.algorithms.leetcode.dynamic;

public class IntegerBreak343 {

    public static void main(String[] args) {
        System.out.println((new IntegerBreak343()).integerBreak3(30));
    }

    /**
     * 1. 暴力搜索
     * 对于给定的一个整数n，穷举它的每一种分解情况，然后对所有情况，求最大值。
     * 并且我们知道，n可以拆成如下情况： <center>图1 整数n分解</center> 通过上图，我们很容易得到一个递归表达式：
     *
     * F(n) = max {i * F(n - i)}，i = 1，2，...，n - 1
     * @param n
     * @return
     */
    // 暴力解法
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            res = Math.max(res, Math.max(i * (n - i), i * integerBreak(n - i)));
        }
        return res;
    }

    /**
     * 对于暴力搜索，通过图1不难得出该方法的时间复杂度为指数级别，显然不能满足题目要求。
     * 那么如此耗时的原因也是因为在递归的过程中计算了很多重复值。
     * 例如，图1中F(n-2)和F(n-3)至少重复计算了两次，并且在后面会有更多次重复运算，
     * 并且我们知道，其实只需要求F(1)，F(2)，...，一直到F(n)，
     * 如果我们每次求完一个F(i)，都将其保存起来，下次再要求的时候直接读取就行了。
     * 既然有这个想法，那么相应的代码应该也不难实现，我们只要用一个数组存放每次的F(i)，具体实现如下：
     */
    // 记忆化搜索-自顶向下
    int[] memory;
    public int integerBreak1(int n) {
        memory = new int[n + 1];
        return integerBreakHelper(n);
    }
    public int integerBreakHelper(int n) {
        if (n == 2) {
            return 1;
        }
        if (memory[n] != 0) {
            return memory[n];
        }
        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            res = Math.max(res, Math.max(i * integerBreakHelper(n - i), i * (n - i)));
        }
        memory[n] = res;
        return res;
    }


    /**
     * 其实，方法2已经满足的题目的要求。但是我们还能这样进一步考虑，将递归转化为递推，
     * 从自顶向下的思路转换为自底向上，这也是记忆化搜索和DP之间最大的区别所在。 代码如下：
     * @param n
     * @return
     */
    // 动态规划
    public int integerBreak3(int n) {
        memory = new int[n + 1];
        memory[2] = 1;
        for (int i = 3; i <= n; i++) {
            for ( int j = 1; j <= i - 1; j++) {
                memory[i] = Math.max(memory[i], Math.max(j * memory[i - j], j * (i - j)));
            }
        }
        return memory[n];
    }
}
