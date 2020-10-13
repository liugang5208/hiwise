package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle118 {

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }
        List<Integer> init = Arrays.asList(1);
        ans.add(init);
        for(int i = 1; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int j = 1; j < i; j++) {
                temp.add(ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1));
            }
            temp.add(1);
            ans.add(temp);
        }
        return ans;
    }
}
