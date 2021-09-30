package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BeautifulArrangement526 {
    /**
     * 526. 优美的排列
     * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
     * perm[i] 能够被 i 整除
     * i 能够被 perm[i] 整除
     * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：
     * 第 1 个优美的排列是 [1,2]：
     *     - perm[1] = 1 能被 i = 1 整除
     *     - perm[2] = 2 能被 i = 2 整除
     * 第 2 个优美的排列是 [2,1]:
     *     - perm[1] = 2 能被 i = 1 整除
     *     - i = 2 能被 perm[2] = 1 整除
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * 提示：
     * 1 <= n <= 15
     */
    List<Integer>[] matchs;
    boolean[] vis;
    int num = 0;
    public int countArrangement(int n) {
       matchs = new List[n + 1];
       vis = new boolean[n + 1];
       for (int i = 0; i <= n; i++) {
           matchs[i] = new ArrayList<>();
       }
       for (int i = 1; i <= n; i++) {
           for (int j = 1; j <= n; j++) {
               if (i % j == 0 || j % i == 0) {
                   matchs[i].add(j);
               }
           }
       }
       backtrace(1, n);
       return num;
    }

    public void backtrace(int index, int n) {
        if (index == n + 1) {
            num++;
            return;
        }
        for (int x : matchs[index]) {
            if (!vis[x]) {
                vis[x] = true;
                backtrace(index + 1, n);
                vis[x] = false;
            }
        }
    }


    /**
     * 方法一：回溯
     * 思路和算法
     * 我们可以使用回溯法解决本题，从左向右依次向目标排列中放入数即可。
     * 具体地，我们定义函数 backtrack(index,n)，表示尝试向位置index 放入数。其中 nn 表示排列的长度。
     * 在当前函数中，我们首先找到一个符合条件的未被使用过的数，然后递归地执行 backtrack(index+1,n)，当该函数执行完毕，回溯到当前层，我们再尝试下一个符合条件的未被使用过的数即可。
     * 回溯过程中，我们可以用vis 数组标记哪些数被使用过，每次我们选中一个数 x，我们就将vis[x] 标记为true，回溯完成后，我们再将其置为false。
     * 特别地，为了优化回溯效率，我们可以预处理每个位置的符合条件的数有哪些，用二维数组match 保存。当我们尝试向位置index 放入数时，我们只需要遍历 match[index] 即可。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/beautiful-arrangement/solution/you-mei-de-pai-lie-by-leetcode-solution-vea2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
