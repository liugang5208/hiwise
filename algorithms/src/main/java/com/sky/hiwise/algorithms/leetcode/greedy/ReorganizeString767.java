package com.sky.hiwise.algorithms.leetcode.greedy;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ReorganizeString767 {
    /**
     * 767. 重构字符串
     * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     * 示例 1:
     * 输入: S = "aab"
     * 输出: "aba"
     * 示例 2:
     * 输入: S = "aaab"
     * 输出: ""
     */
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        int len = S.length();
        int[] counts = new int[26];
        int maxChar = 0;
        for (char c : S.toCharArray()) {
            counts[c - 'a']++;
            maxChar = Math.max(maxChar, counts[c - 'a']);
        }

        if (maxChar > (len + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>(){
            @Override
            public int compare(Character c1, Character c2) {
                return counts[c2 - 'a'] - counts[c1 - 'a'];
            }
        });

        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                pq.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (pq.size() > 1) {
            char let1 = pq.poll();
            char let2 = pq.poll();
            sb.append(let1);
            sb.append(let2);
            int idx1 = let1 - 'a', idx2 = let2 - 'a';
            counts[idx1]--;
            counts[idx2]--;
            if (counts[idx1] > 0) {
                pq.add(let1);
            }
            if (counts[idx2] > 0) {
                pq.add(let2);
            }
        }
        if (pq.size() > 0) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }
    /**
     * 前言
     * 这道题是典型的贪心算法的题。重构字符串时，需要根据每个字母在字符串中出现的次数处理每个字母放置的位置。如果出现次数最多的字母可以在重新排布之后不相邻，则可以重新排布字母使得相邻的字母都不相同。
     * 如果出现次数最多的字母过多，则无法重新排布字母使得相邻的字母都不相同。
     *
     * 假设字符串的长度为 nn，如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现多少次？
     *
     * 当 nn 是偶数时，有 n/2n/2 个偶数下标和 n/2n/2 个奇数下标，因此每个字母的出现次数都不能超过 n/2n/2 次，否则出现次数最多的字母一定会出现相邻。
     *
     * 当 nn 是奇数时，由于共有 (n+1)/2(n+1)/2 个偶数下标，因此每个字母的出现次数都不能超过 (n+1)/2(n+1)/2 次，否则出现次数最多的字母一定会出现相邻。
     *
     * 由于当 nn 是偶数时，在整数除法下满足 n/2n/2 和 (n+1)/2(n+1)/2 相等，因此可以合并 nn 是偶数与 nn 是奇数的情况：如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现 (n+1)/2(n+1)/2 次。
     *
     * 因此首先遍历字符串并统计每个字母的出现次数，如果存在一个字母的出现次数大于 (n+1)/2(n+1)/2，则无法重新排布字母使得相邻的字母都不相同，返回空字符串。
     * 如果所有字母的出现次数都不超过 (n+1)/2(n+1)/2，则考虑如何重新排布字母。
     *
     * 以下提供两种使用贪心算法的方法，分别基于最大堆和计数。
     *
     * 方法一：基于最大堆的贪心算法
     * 维护最大堆存储字母，堆顶元素为出现次数最多的字母。首先统计每个字母的出现次数，然后将出现次数大于 00 的字母加入最大堆。
     *
     * 当最大堆的元素个数大于 11 时，每次从最大堆取出两个字母，拼接到重构的字符串，然后将两个字母的出现次数分别减 11，并将剩余出现次数大于 00 的字母重新加入最大堆。
     * 由于最大堆中的元素都是不同的，因此取出的两个字母一定也是不同的，将两个不同的字母拼接到重构的字符串，可以确保相邻的字母都不相同。
     *
     * 如果最大堆变成空，则已经完成字符串的重构。如果最大堆剩下 11 个元素，则取出最后一个字母，拼接到重构的字符串。
     *
     * 对于长度为 nn 的字符串，共有 n/2n/2 次每次从最大堆取出两个字母的操作，当 nn 是奇数时，还有一次从最大堆取出一个字母的操作，因此重构的字符串的长度一定是 nn。
     *
     * 当 nn 是奇数时，是否可能出现重构的字符串的最后两个字母相同的情况？如果最后一个字母在整个字符串中至少出现了 22 次，
     * 则在最后一次从最大堆取出两个字母时，该字母会先被选出，因此不会成为重构的字符串的倒数第二个字母，也不可能出现重构的字符串最后两个字母相同的情况。
     *
     * 因此，在重构字符串可行的情况下，基于最大堆的贪心算法可以确保得到正确答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reorganize-string/solution/zhong-gou-zi-fu-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
