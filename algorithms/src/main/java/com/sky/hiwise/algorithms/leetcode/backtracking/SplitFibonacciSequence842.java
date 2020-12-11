package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class SplitFibonacciSequence842 {
    /**
     * 842. 将数组拆分成斐波那契序列
     * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
     * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
     * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
     * F.length >= 3；
     * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
     * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
     * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
     * 示例 1：
     * 输入："123456579"
     * 输出：[123,456,579]
     * 示例 2：
     * 输入: "11235813"
     * 输出: [1,1,2,3,5,8,13]
     */
    int len;
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        if (S == null || S.length() < 3) {
            return ans;
        }
        len = S.length();
        return backtrace(0, S, ans) ? ans : new ArrayList<>();
    }

    public boolean backtrace(int idx, String s, List<Integer> ans) {
        int size = ans.size();
        if (idx == len) {
            return size > 2;
        }
        int num = 0;
        for (int i = idx; i < len; i++) {
            num = num * 10 + s.charAt(i) - '0';
            //判断是否溢出
            if (num < 0) {
                return false;
            }
            if (size < 2 || num == ans.get(size - 1) + ans.get(size - 2)) {
                ans.add(num);
                if (backtrace(i + 1, s, ans)) {
                    return true;
                }
                ans.remove(size);
                //ans.remove(ans.size() - 1);
            }
            //判断是否以0开头，阻止循环到下一位
            if (i == idx && s.charAt(i) - '0' == 0) {
                return false;
            }
        }
        return false;
    }

}
