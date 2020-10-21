package com.sky.hiwise.algorithms.leetcode.array;

public class SquaresSortedArray977 {

    /**
     * 977. 有序数组的平方
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     * 示例 1：
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 示例 2：
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] ans = new int[len];
        for (int start = 0, end = len - 1, pos = len - 1; start <= end; ) {
            int startSqu = A[start] * A[start];
            int endSqu = A[end] * A[end];
            if (startSqu > endSqu) {
                ans[pos] = startSqu;
                start ++;
            } else {
                ans[pos] = endSqu;
                end--;
            }
            pos--;
        }
        return ans;
    }
}
