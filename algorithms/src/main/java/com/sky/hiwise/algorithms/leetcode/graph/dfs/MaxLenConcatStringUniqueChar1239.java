package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.List;

public class MaxLenConcatStringUniqueChar1239 {
    /**
     * 1239. 串联字符串的最大长度
     * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
     * 请返回所有可行解 s 中最长长度。
     * 示例 1：
     * 输入：arr = ["un","iq","ue"]
     * 输出：4
     * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
     * 示例 2：
     * 输入：arr = ["cha","r","act","ers"]
     * 输出：6
     * 解释：可能的解答有 "chaers" 和 "acters"。
     * 示例 3：
     * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
     * 输出：26
     */

    int maxLen = 0;
    public int maxLength(List<String> arr) {
        dfs(arr, 0, 0, 0);
        return maxLen;
    }

    private void dfs(List<String> arr, int index, int ck, int currMax) {
        if (index == arr.size()) {
            maxLen = Math.max(maxLen, currMax);
            return;
        }
        String curr = arr.get(index);
        int tempCK = getCK(ck, curr);
        if (tempCK != -1) {
            dfs(arr, index + 1, tempCK, currMax + curr.length());
        }
        dfs(arr, index + 1, ck, currMax);
    }

    private int getCK(int ck, String curr) {
        char[] chars = curr.toCharArray();
        for (char c : chars) {
            int tempInt = c - 'a';
            if ((ck & (1 << tempInt)) != 0 ) {
                return -1;
            }
            ck = ck | (1 << tempInt);
        }
        return ck;
    }

    /**
     * 按照上面的例子，一共有16中情况，DFS流程为：
     * 从第一个字符串开始遍历
     * 两种情况，第一种是加入第一个字符串
     * 第二种是不要第一个字符串
     * 遇到第二个字符串，比较第一个字符串和第二个字符串是否有相同的字符
     * 如果相同，跳过第二个字符，如果不同，把第二个字符加入
     * 如果字符串到达List的最后一个，结束。否则，跳转到第一步
     *
     * 作者：wxxlamp
     * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/solution/dfs-he-wei-ya-suo-by-stalern/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    //说句题外话，查看两个字符串（只含有小写字符）是否有相同字符的方法：
    //使用一个一维数组，遍历两边字符串。空间复杂度O(1)，时间复杂度O(n)
    private boolean notEqual(String a, String b) {
        int[] charArray = new int[30];
        for (int i = 0; i < a.length(); i ++) {
            charArray[a.charAt(i) - 'a'] = 1;
        }
        for (int i = 0; i < b.length(); i ++) {
            if (charArray[b.charAt(i) - 'a'] == 1) {
                return false;
            }
        }
        return true;
    }
    //使用位运算：ck & (1 << i) == 0
    //其中，ck是一个最少26byte的字符，从左至右，每一位分别代表a,b,,,x,y,x
    //i代表字符串中的字符
    // 比较str和之前ck代表的字符串是否有相同字符，如果没有，则把str代表的ck加去，如果有，则返回-1
    private int notEqual(int ck, String str) {
        char[] chars = str.toCharArray();
        for(char c: chars) {
            if ((ck & 1 << (c - 'a')) != 0) {
                return -1;
            }
            ck = ck | 1 << (c - 'a');
        }
        return ck;
    }

}
