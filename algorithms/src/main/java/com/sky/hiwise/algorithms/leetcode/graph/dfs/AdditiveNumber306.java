package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2023-05-23 16:08
 **/
public class AdditiveNumber306 {

    /**
     * 306. 累加数
     * 累加数 是一个字符串，组成它的数字可以形成累加序列。
     * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
     * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
     * 说明：累加序列里的数，除数字 0 之外，不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
     * 示例 1：
     * 输入："112358"
     * 输出：true
     * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * 示例 2：
     * 输入："199100199"
     * 输出：true
     * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
     * @param num
     * @return
     */
    String num;
    int n;
    List<List<Integer>> list = new ArrayList<>();
    public boolean isAdditiveNumber(String num) {
         n = num.length();
         this.num = num;
         return dfs(0);
    }

    public boolean dfs(int idx) {
        int m = list.size();
        if (idx == n) {
            return m >= 3;
        }
        int max = num.charAt(idx) == '0' ? idx + 1 : n;
        List<Integer> cur = new ArrayList<>();
        for (int i = idx; i < max; i++) {
            cur.add(0, num.charAt(i) - '0');
            if (m < 2 || check(list.get(m - 2), list.get(m - 1), cur)) {
                list.add(cur);
                if (dfs(i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    public boolean check(List<Integer> a, List<Integer> b, List<Integer> c) {
        List<Integer> ans = new ArrayList<>();
        int carry = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size()) {
                carry += a.get(i);
            }
            if (i < b.size()) {
                carry += b.get(i);
            }
            ans.add(carry % 10);
            carry = carry / 10;
        }
        if (carry > 0) {
            ans.add(carry);
        }
        boolean ok = ans.size() == c.size();
        for (int i = 0; i < c.size() && ok; i++) {
            if (c.get(i) != ans.get(i)) {
                ok = false;
            }
        }
        return  ok;
    }


    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(0, i);
//        }
//        for (Integer i : list) {
//            System.out.println(i);
//        }
        AdditiveNumber306 an = new AdditiveNumber306();
        String input = "121202436";
        System.out.println(an.isAdditiveNumber(input));
    }
}
