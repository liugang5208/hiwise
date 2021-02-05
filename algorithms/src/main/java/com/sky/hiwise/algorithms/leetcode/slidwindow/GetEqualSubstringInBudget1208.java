package com.sky.hiwise.algorithms.leetcode.slidwindow;

public class GetEqualSubstringInBudget1208 {
    /**
     * 1208. 尽可能使字符串相等
     * 给你两个长度相同的字符串，s 和 t。
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     * 示例 1：
     * 输入：s = "abcd", t = "bcdf", cost = 3
     * 输出：3
     * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
     * 示例 2：
     * 输入：s = "abcd", t = "cdef", cost = 3
     * 输出：1
     * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
     * 示例 3：
     * 输入：s = "abcd", t = "acde", cost = 0
     * 输出：1
     * 解释：你无法作出任何改动，所以最大长度为 1。
     * 提示：
     * 1 <= s.length, t.length <= 10^5
     * 0 <= maxCost <= 10^6
     * s 和 t 都只含小写英文字母。
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLen = 0, sum = 0, start = 0, end = 0;
        while (end < n) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start++];
            }
            maxLen = Math.max(maxLen, end - start + 1);
            end++;
        }
        return maxLen;
    }
    /**
     * 方法二：双指针
     * 由于 \textit{diff}diff 的的每个元素都是非负的，因此可以用双指针的方法得到符合要求的最长子数组的长度。
     * 双指针法的思想是，维护两个指针 \textit{start}start 和 \textit{end}end 表示数组 \textit{diff}diff 的子数组的开始下标和结束下标，满足子数组的元素和不超过 \textit{maxCost}maxCost，子数组的长度是 \textit{end}-\textit{start}+1end−start+1。初始时，\textit{start}start 和 \textit{end}end 的值都是 00。
     * 另外还要维护子数组的元素和 \textit{sum}sum，初始值为 00。在移动两个指针的过程中，更新 \textit{sum}sum 的值，判断子数组的元素和是否大于 \textit{maxCost}maxCost，并决定应该如何移动指针。
     * 为了得到符合要求的最长子数组的长度，应遵循以下两点原则：
     * 当 \textit{start}start 的值固定时，\textit{end}end 的值应尽可能大；
     * 当 \textit{end}end 的值固定时，\textit{start}start 的值应尽可能小。
     * 基于上述原则，双指针的做法如下：
     * 将 \textit{diff}[\textit{end}]diff[end] 的值加到 \textit{sum}sum；
     * 如果 \textit{sum} \le \textit{maxCost}sum≤maxCost，则子数组的元素和不超过 \textit{maxCost}maxCost，使用当前子数组的长度 \textit{end}-\textit{start}+1end−start+1 更新最大子数组的长度；
     * 如果 \textit{sum}>\textit{maxCost}sum>maxCost，则子数组的元素和大于 \textit{maxCost}maxCost，需要向右移动指针 \textit{start}start 并同时更新 \textit{sum}sum 的值，直到 \textit{sum} \le \textit{maxCost}sum≤maxCost，此时子数组的元素和不超过 \textit{maxCost}maxCost，使用子数组的长度 \textit{end}-\textit{start}+1end−start+1 更新最大子数组的长度；
     * 将指针 \textit{end}end 右移一位，重复上述步骤，直到 \textit{end}end 超出数组下标范围。
     * 遍历结束之后，即可得到符合要求的最长子数组的长度，即字符串可以转化的最大长度。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget/solution/jin-ke-neng-shi-zi-fu-chuan-xiang-deng-b-higz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
