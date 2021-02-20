package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle119 {

    /**
     * 119. 杨辉三角 II
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * 示例:
     * 输入: 3
     * 输出: [1,3,3,1]
     * 进阶：
     * 你可以优化你的算法到 O(k) 空间复杂度吗？
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> init = Arrays.asList(1);
        ans.add(init);
        for(int i = 1; i <= rowIndex; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int j = 1; j < i; j++) {
                temp.add(ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1));
            }
            temp.add(1);
            ans.add(temp);
        }
        return ans.get(rowIndex);
    }
}
