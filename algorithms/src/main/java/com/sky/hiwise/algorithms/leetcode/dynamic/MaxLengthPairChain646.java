package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class MaxLengthPairChain646 {

    /**
     * 646. 最长数对链
     * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
     * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
     * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
     * 示例 :
     * 输入: [[1,2], [2,3], [3,4]]
     * 输出: 2
     * 解释: 最长的数对链是 [1,2] -> [3,4]
     * 注意：
     * 给出数对的个数在 [1, 1000] 范围内。
     * @param pairs
     * @return
     */
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b)->{
            return a[1] - b[1];
        });

        int ans = 1;
        int pre = 0;
        for(int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > pairs[pre][1]) {
                ans ++;
                pre = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] pairs = {{9,10},{-4,9},{-5,6},{-5,9},{8,9}};
        System.out.println(findLongestChain(pairs));
    }
}
