package com.sky.hiwise.algorithms.leetcode.greedy;

public class IncreasingDigits738 {
    /**
     * 738. 单调递增的数字
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     *
     * 示例 1:
     *
     * 输入: N = 10
     * 输出: 9
     * 示例 2:
     *
     * 输入: N = 1234
     * 输出: 1234
     * 示例 3:
     *
     * 输入: N = 332
     * 输出: 299
     */
    public int monotoneIncreasingDigits(int N) {
        char[] chN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < chN.length && chN[i - 1] <= chN[i]) {
            i++;
        }
        if (i < chN.length) {
            while (i > 0 && chN[i - 1] > chN[i]) {
                chN[i - 1]--;
                i--;
            }
            for(i += 1; i < chN.length; i++) {
                chN[i] = '9';
            }
        }
        return Integer.parseInt(new String(chN));
    }
    /**
     * 方法一：贪心
     * 我们可以从高到低按位构造这个小于等于 NN 的最大单调递增的数字。假设不考虑 NN 的限制，那么对于一个长度为 nn 的数字，最大单调递增的数字一定是每一位都为 99 的数字。
     *
     * 记 \textit{strN}[i]strN[i] 表示数字 NN 从高到低的第 ii 位的数字（ii 从 00 开始）。
     *
     * 如果整个数字 NN 本身已经是按位单调递增的，那么最大的数字即为 NN。
     *
     * 如果找到第一个位置 ii 使得 [0,i-1][0,i−1] 的数位单调递增且 \textit{strN}[i-1]>\textit{strN}[i]strN[i−1]>strN[i]，此时 [0,i][0,i] 的数位都与 NN 的对应数位相等，仍然被 NN 限制着，即我们不能随意填写 [i+1,n-1][i+1,n−1] 位置上的数字。为了得到最大的数字，我们需要解除 NN 的限制，来让剩余的低位全部变成 99 ，即能得到小于 NN 的最大整数。而从贪心的角度考虑，我们需要尽量让高位与 NN 的对应数位相等，故尝试让 \textit{strN}[i-1]strN[i−1] 自身数位减 11。此时已经不再受 NN 的限制，直接将 [i, n-1][i,n−1] 的位置上的数全部变为 99 即可。
     *
     * 但这里存在一个问题：当 \textit{strN}[i-1]strN[i−1] 自身数位减 11 后可能会使得 \textit{strN}[i-1]strN[i−1] 和 \textit{strN}[i-2]strN[i−2] 不再满足递增的关系，因此我们需要从 i-1i−1 开始递减比较相邻数位的关系，直到找到第一个位置 jj 使得 \textit{strN}[j]strN[j] 自身数位减 11 后 \textit{strN}[j-1]strN[j−1] 和 \textit{strN}[j]strN[j] 仍然保持递增关系，或者位置 jj 已经到最左边（即 jj 的值为 00），此时我们将 [j+1,n-1][j+1,n−1] 的数全部变为 99 才能得到最终正确的答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits/solution/dan-diao-di-zeng-de-shu-zi-by-leetcode-s-5908/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
