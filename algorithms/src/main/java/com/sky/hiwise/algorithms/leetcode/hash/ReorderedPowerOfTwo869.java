package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReorderedPowerOfTwo869 {
    /**
     * 869. 重新排序得到 2 的幂
     * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
     * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
     * 示例 1：
     * 输入：1
     * 输出：true
     * 示例 2：
     * 输入：10
     * 输出：false
     * 示例 3：
     * 输入：16
     * 输出：true
     * 示例 4：
     * 输入：24
     * 输出：false
     * 示例 5：
     * 输入：46
     * 输出：true
     */
    public boolean reorderedPowerOf2(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < (int)1e9 + 10; i *= 2) {
            set.add(i);
        }
        int[] cnt = new int[10];
        while (n != 0) {
            cnt[n % 10] ++;
            n /= 10;
        }
        int[] curr = new int[10];
        out : for (int x : set) {
            Arrays.fill(curr, 0);
            while (x != 0) {
                curr[x % 10] ++;
                x /= 10;
            }
            for (int i = 0; i < 10; i++) {
                if (cnt[i] != curr[i]) {
                   continue out;
                }
            }
            return true;
        }
        return false;
    }
}
