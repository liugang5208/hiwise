package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.Arrays;

public class LongestHappyString1405 {

    /**
     * 1405. 最长快乐字符串
     * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
     * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
     * s 是一个尽可能长的快乐字符串。
     * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
     * s 中只含有 'a'、'b' 、'c' 三种字母。
     * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
     * 示例 1：
     * 输入：a = 1, b = 1, c = 7
     * 输出："ccaccbcc"
     * 解释："ccbccacc" 也是一种正确答案。
     * 示例 2：
     * 输入：a = 2, b = 2, c = 1
     * 输出："aabbc"
     */
    public String longestDiverseString(int a, int b, int c) {
        Pair[] pairs = new Pair[] {
                new Pair(a, 'a'),
                new Pair(b, 'b'),
                new Pair(c, 'c')
        };
        StringBuilder sb = new StringBuilder();

        while (true) {
            boolean hasNext = false;
            Arrays.sort(pairs, (p1, p2) -> p2.freq - p1.freq);
            for (Pair pair : pairs) {
                if (pair.freq <= 0) {
                    break;
                }
                int m = sb.length();
                if (m >= 2 && sb.charAt(m - 2) == pair.c && sb.charAt(m - 1) == pair.c) {
                    continue;
                }
                hasNext = true;
                sb.append(pair.c);
                pair.freq--;
                break;
            }
            if (!hasNext) {
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 方法一：贪心
     * 思路
     * 题目要求找到最长的快乐字符串，且快乐字符串中不含有三个连续相同的字母。为了找到最长的字符串，我们可以使用如下贪心策略：
     * 尽可能优先使用当前数量最多的字母，因为最后同一种字母剩余的越多，越容易出现字母连续相同的情况。如果构建完成最长的快乐字符串后还存在剩余未选择的字母，则剩余的字母一定为同一种字母且该字母的总数量最多。
     * 依次从当前数量最多的字母开始尝试，如果发现加入当前字母会导致出现三个连续相同字母，则跳过当前字母，直到我们找到可以添加的字母为止。实际上每次只会在数量最多和次多的字母中选择一个。
     * 如果尝试所有的字母都无法添加，则直接退出，此时构成的字符串即为最长的快乐字符串。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-happy-string/solution/zui-chang-kuai-le-zi-fu-chuan-by-leetcod-5nde/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Pair {
        int freq;
        char c;

        public Pair(int freq, char c) {
            this.freq = freq;
            this.c = c;
        }
    }
}
