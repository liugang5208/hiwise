package com.sky.hiwise.algorithms.leetcode.dynamic;

public class MinFallingPathSum1289 {
    /**
     * 1289. 下降路径最小和  II
     * 给你一个整数方阵 arr ，定义「非零偏移下降路径」为：从 arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
     * 请你返回非零偏移下降路径数字和的最小值。
     * 示例 1：
     * 输入：arr = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：13
     * 解释：
     * 所有非零偏移下降路径包括：
     * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
     * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
     * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
     * 下降路径中数字和最小的是 [1,5,7] ，所以答案是 13 。
     */
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        int minSum = 0, nextMinSum = 0, minPos = -1;
        for (int i = 0; i < n; i++) {
            int ms = Integer.MAX_VALUE, nms = -1, mp = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int currSum = ((j == minPos) ? nextMinSum : minSum) + arr[i][j];
                if (currSum < ms) {
                    //找到currSum比最小值ms更小 ms赋给次小值 currSum赋给最小值
                    mp = j;
                    nms = ms;
                    ms = currSum;
                } else if (currSum < nms) {
                    nms = currSum;
                }
            }
            minSum = ms;
            nextMinSum = nms;
            minPos = mp;
        }
        return minSum;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println((new MinFallingPathSum1289()).minFallingPathSum(arr));
    }
    /**
     * 此外，我们还可以对空间复杂度进行优化。由于 f[i][j] 只会从 f[i - 1][jmin[i - 1]] 或 f[i - 1][jnext[i - 1]] 转移而来，那么我们并不用将第 i - 1 行的所有状态存储下来，而是可以浓缩成三个变量：
     * first_sum 表示这一行的最小值；
     * first_pos 表示这一行最小值对应的 jmin；
     * second_sum 表示这一行的次小值。
     * 状态转移方程修改为：
     * f[i][j] = first_sum + arr[i][j]    其中 j != first_pos
     * f[i][j] = second_sum + arr[i][j]   其中 j == first_pos
     * 通过这三个变量计算出第 i 行的所有状态之后，我们也不用将它们存储下来，同样可以浓缩成三个变量，为第 i + 1 行的动态规划提供转移基础。由于在计算第 i + 1 行的状态时，不需要第 i - 1 行的任何信息，因此第 i - 1 行浓缩成的三个变量此时可以被丢弃。这样以来，我们就将空间复杂度从 O(N^2)O(N
     * 2
     *  ) 降低至了 O(1)O(1)。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum-ii/solution/xia-jiang-lu-jing-zui-xiao-he-ii-by-leetcode-solut/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
