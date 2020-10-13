package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Permutations46 {
    /**
     * 46给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<>(), visited);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (nums.length == tmp.size()) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            tmp.add(nums[i]);
            visited[i] = 1;
            backtrack(res, nums, tmp, visited);
            tmp.remove(tmp.size() - 1);
            visited[i] = 0;
        }
    }

    public static void main(String[] args) {
        int nums[] = new int[]{1, 2, 3};
        System.out.println((new Permutations46()).permute(nums));
        System.out.println((new Permutations46()).permuteUnique(nums));
    }

    /**
     * 47给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 示例:
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     * @param nums
     * @return
     */
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        // 修改 1：首先排序，之后才有可能发现重复分支
        Arrays.sort(nums);

        // 如果是降序，需要把 nums 变为包装数组类型，输入 Arrays.sort() 方法才生效，并且还要传入一个比较器，搜索之前，再转为基本类型数组，因此不建议降序排序
        // Integer[] numsBoxed = IntStream.of(nums).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
        // Arrays.sort(numsBoxed, Collections.reverseOrder());
        // nums = Arrays.stream(numsBoxed).mapToInt(Integer::valueOf).toArray();

        used = new boolean[len];
        findPermuteUnique(nums, 0, new Stack<>());
        return res;
    }

    private void findPermuteUnique(int[] nums, int depth, Stack<Integer> stack) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < nums.length; i ++) {
            if (!used[i]) {
                // 修改 2：因为排序以后重复的数一定不会出现在开始，故 i > 0
                // 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
                // 这种情况跳过即可
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                stack.add(nums[i]);
                findPermuteUnique(nums, depth + 1, stack);
                stack.pop();
                used[i] = false;
            }
        }
    }
}
