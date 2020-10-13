package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {

    /**
     * 77 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * 示例:
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * @param n
     * @param k
     * @return
     */
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) {
           return  res;
        }
        List<Integer> temp = new ArrayList<>();
        generateCombinations(n, k, 1, temp);
        return res;
    }

    private void generateCombinations(int n, int k, int start, List<Integer> temp) {
        if (k == temp.size()) {
            res.add(new ArrayList<>(temp));
            return;
        }

        //剪枝
        //还有k-temp.size个空位，所以i...n中至少要有k-temp.size个元素
        //i最多为n-(k-temp.size)+1
        for (int i = start; i <= n - (k - temp.size()) + 1; i++) {
            temp.add(i);
            generateCombinations(n, k, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println((new Combinations77()).combine(4, 2));
    }
}
