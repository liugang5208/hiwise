package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class RussianDollEnvelopes354 {

    /**
     * 354. 俄罗斯套娃信封问题
     * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，
     * 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * 说明:
     * 不允许旋转信封。
     * 示例:
     * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出: 3
     * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len <= 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int[] ends = new int[len];
        for(int i = 0; i < len; i++) {
            ends[i] = envelopes[i][1];
        }

        int[] memo = new int[len + 1];
        Arrays.fill(memo, 1);
        int res = 1;
        for(int i = 1; i < len; i++) {
            for(int j = 0; j < i; j ++) {
                if (ends[i] > ends[j]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                    res = Math.max(res, memo[i]);
                }
            }
        }

        int result = 0;
        int[] tails = new int[len];
        for(int i = 0; i < len; i++) {
            int start = 0, end = result;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (ends[i] > tails[mid]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            tails[start] = ends[i];
            if (end == result) {
                result ++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] envelopes =  new int[][]{{5,4},{6,4},{6,7},{2,3}};
        System.out.println((new RussianDollEnvelopes354()).maxEnvelopes(envelopes));
    }
}
