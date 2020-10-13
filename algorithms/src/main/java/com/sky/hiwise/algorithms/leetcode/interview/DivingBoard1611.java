package com.sky.hiwise.algorithms.leetcode.interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DivingBoard1611 {

    /**
     * 面试题 16.11. 跳水板
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     * 返回的长度需要从小到大排列。
     * 示例：
     * 输入：
     * shorter = 1
     * longer = 2
     * k = 3
     * 输出： {3,4,5,6}
     * 提示：
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= k; i++) {
            int temp = i * shorter + longer * (k - i);
            set.add(temp);
        }
        int[] ans = new int[set.size()];
        int i = 0;
        for(Integer v : set) {
            ans[i++] = v;
        }
        Arrays.sort(ans);
        return ans;
    }

    public int[] divingBoard1(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] ans = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            //i * longer 先算大值 i从小到大
            ans[i] = i * longer + shorter * (k - i);
        }
        return ans;
    }
}
