package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.*;

public class CombinationSum39 {

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2:
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates.length == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        generateCombinations(candidates, 0 , target, temp);
        return res;
    }

    private void generateCombinations(int[] candidates, int start, int target, List<Integer> temp) {

        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i ++ ) {
            if (target >= candidates[i]) {
                temp.add(candidates[i]);
                generateCombinations(candidates, i,target - candidates[i], temp);
                temp.remove(temp.size() -1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        System.out.println((new CombinationSum39()).combinationSum(candidates, target));
        System.out.println((new CombinationSum39()).combinationSum40(candidates, target));
    }

    /**
     * 40给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum40(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        // 先将数组排序，这一步很关键
        Arrays.sort(candidates);
        generateCombinations40(candidates, 0 , target, temp);
        return res;
    }

    private void generateCombinations40(int[] candidates, int start, int target, List<Integer> temp) {

        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i ++ ) {
            // 这一步剪枝操作基于 candidates 数组是排序数组的前提下
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target >= candidates[i]) {
                temp.add(candidates[i]);
                // 【关键】因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
                generateCombinations40(candidates, i + 1,target - candidates[i], temp);
                temp.remove(temp.size() -1);
            }
        }
    }

    /**
     * 216 找出所有相加之和为 n 的 k 个数的组合。
     * 组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum216(int k, int n) {
        if (k <= 0 || n <= 0) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        combinationSum216(k, n, 1, temp);
        return res;
    }

    private void combinationSum216(int k, int n, int start, List<Integer> temp) {
        if (n == 0 && temp.size() == k) {
           res.add(new ArrayList<>(temp));
           return;
        }
        for(int i = start; i <= 9; i ++) {
            temp.add(i);
            combinationSum216(k, n - i, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
