package com.sky.hiwise.algorithms.leetcode.string;

public class NumOfSegmentsInString434 {
    /**
     * 434. 字符串中的单词数
     * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
     * 请注意，你可以假定字符串里不包括任何不可打印的字符。
     * 示例:
     * 输入: "Hello, my name is John"
     * 输出: 5
     * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
     * 通过次数60,832提交次数152,534
     */
    public int countSegments(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                ans ++;
            }
        }
        return ans;
    }
    /**
     * 方法一：原地法
     * 思路与算法
     * 计算字符串中单词的数量，就等同于计数单词的第一个下标的个数。因此，我们只需要遍历整个字符串，统计每个单词的第一个下标的数目即可。
     * 满足单词的第一个下标有以下两个条件：
     * 该下标对应的字符不为空格；
     * 该下标为初始下标或者该下标的前下标对应的字符为空格；
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string/solution/zi-fu-chuan-zhong-de-dan-ci-shu-by-leetc-igfb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
